package com.mby.springcloud.catalogservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean


@EnableDiscoveryClient
@SpringBootApplication
class CatalogServiceApp {
    @Bean
    fun objectMapper(): ObjectMapper = jacksonObjectMapper()
}

fun main(args: Array<String>) {
    runApplication<CatalogServiceApp>(*args)
}