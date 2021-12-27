package com.mby.springcloud.userservice

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user-service")
class PingController(
    private val env: Environment
) {

    @GetMapping("/health_check")
    fun status() = "Its Working in User Service ${env.getProperty("local.server.port")}"

    @GetMapping("/welcome")
    fun welcome() = env.getProperty("welcome.message")
}