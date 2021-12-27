package com.mby.springcloud.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@EnableDiscoveryClient
@SpringBootApplication
open class UserServiceApp

fun main(args: Array<String>) {
    runApplication<UserServiceApp>(*args)
}