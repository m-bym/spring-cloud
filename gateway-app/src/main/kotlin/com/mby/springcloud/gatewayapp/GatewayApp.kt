package com.mby.springcloud.gatewayapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class GatewayApp

fun main(args: Array<String>) {
    runApplication<GatewayApp>(*args)
}