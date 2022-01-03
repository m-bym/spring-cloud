package com.mby.springcloud.userservice.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.mby.springcloud.userservice.domain.UserService
import com.mby.springcloud.userservice.dto.RequestLogin
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val userService: UserService,
    private val env: Environment,
    private val objectMapper: ObjectMapper
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val requestLogin = objectMapper.readValue(request.inputStream, RequestLogin::class.java)
        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                requestLogin.email,
                requestLogin.pwd,
                listOf()
            )
        )
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val userDetail = authResult.principal as User
        val userDto = userService.getUserByEmail(userDetail.username)

        val token = Jwts.builder()
            .setSubject(userDto.id.toString())
            .setExpiration(Date(System.currentTimeMillis() + env.getProperty("token.expiration_time")!!.toLong()))
            .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
            .compact()

        response.addHeader("token", token)
        response.addHeader("userId", userDto.id.toString())

        println("@@ ${(authResult.principal as User).username}")
    }
}