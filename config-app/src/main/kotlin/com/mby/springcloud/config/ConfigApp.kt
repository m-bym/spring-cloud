package com.mby.springcloud.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@EnableConfigServer
@SpringBootApplication
class ConfigApp

fun main(args: Array<String>) {
    runApplication<ConfigApp>(*args)
}