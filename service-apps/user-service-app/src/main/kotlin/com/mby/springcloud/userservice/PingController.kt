package com.mby.springcloud.userservice

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class PingController(
    private val env: Environment
) {

    @GetMapping("/health_check")
    fun status() = "Its Working in User Service " +
            "port : ${env.getProperty("local.server.port")} " +
            "token secret : ${env.getProperty("token.secret")} " +
            "token time : ${env.getProperty("token.expiration_time")}"

    @GetMapping("/welcome")
    fun welcome() = env.getProperty("welcome.message")
}