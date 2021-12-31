package com.mby.springcloud.gateway.filter

import io.jsonwebtoken.Jwts
import org.apache.http.HttpHeaders
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AuthorizationHeaderFilter(private val env: Environment) :
    AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {
    class Config

    // login -> token -> users (with token) -> header(include token)
    override fun apply(config: Config) = GatewayFilter { exchange, chain ->
        val request = exchange.request
        if (!request.headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            throw UnAuthorizedException("No authorization header")
        }

        val authorizationHeader = request.headers.get(HttpHeaders.AUTHORIZATION)!![0]
        val jwt = authorizationHeader.replace("Bearer ", "")
        if (!isJwtValid(jwt)) {
            throw UnAuthorizedException("invalid token")
        }

        chain.filter(exchange).then(Mono.fromRunnable {
        })
    }

    private fun isJwtValid(jwt: String): Boolean {
        try {
            val secretKey = env.getProperty("token.secret")
            val subject = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).body.subject
            if (subject.isNullOrEmpty()) return false
        } catch (ex: Exception) {
            return false
        }

        return true
    }
}

class UnAuthorizedException(override val message: String) : RuntimeException(message)