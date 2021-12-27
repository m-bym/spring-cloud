package com.mby.springcloud.discoveryapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
open class DiscoveryApp

fun main(args: Array<String>) {
    runApplication<DiscoveryApp>(*args)
}