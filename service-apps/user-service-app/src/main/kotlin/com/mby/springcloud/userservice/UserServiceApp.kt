package com.mby.springcloud.userservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mby.springcloud.userservice.infra.FeignErrorDecoder
import feign.Logger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.client.RestTemplate


@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
class UserServiceApp {
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @LoadBalanced
    fun restTemplate(): RestTemplate = RestTemplate()

    @Bean
    fun objectMapper(): ObjectMapper = jacksonObjectMapper()

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun feignErrorDecoder(): FeignErrorDecoder = FeignErrorDecoder()
}

fun main(args: Array<String>) {
    runApplication<UserServiceApp>(*args)
}