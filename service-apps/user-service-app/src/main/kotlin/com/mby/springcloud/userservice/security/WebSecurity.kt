package com.mby.springcloud.userservice.security

import com.mby.springcloud.userservice.domain.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurity(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userService: UserService,
    private val env: Environment
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .addFilter(authenticationFilter())

        http.headers().frameOptions().disable()
    }

    @Bean
    fun authenticationFilter(): AuthenticationFilter {
        return AuthenticationFilter(authenticationManager(), userService, env)
    }

    //인증 관련 configure
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }
}