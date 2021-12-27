package com.mby.springcloud.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ConfigApp

fun main(args: Array<String>) {
    runApplication<ConfigApp>(*args)
}