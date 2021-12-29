package com.mby.springcloud.orderservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@EnableDiscoveryClient
@SpringBootApplication
class OrderServiceApp

fun main(args: Array<String>) {
    runApplication<OrderServiceApp>(*args)
}