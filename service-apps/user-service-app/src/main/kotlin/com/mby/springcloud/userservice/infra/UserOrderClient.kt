package com.mby.springcloud.userservice.infra

import com.mby.springcloud.userservice.infra.dto.OrderPayload
import org.slf4j.LoggerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class UserOrderClient(
    //private val restTemplate: RestTemplate,
    private val orderServiceClient: OrderServiceClient,
    private val env: Environment,
    private val circuitBreakerFactory: Resilience4JCircuitBreakerFactory
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun getUserOrders(userId: Long): List<OrderPayload> {
        /*
        val orderUrl = env.getProperty("order_service.url")!!.format(userId)
        return restTemplate.exchange(
            orderUrl,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<OrderPayload>>() {}
        ).body ?: emptyList()
         */

        /* Using a feign client with circuitBreaker */
        val circuitBreaker = circuitBreakerFactory.create("circuitbreaker")
        return circuitBreaker.run({ orderServiceClient.getOrders(userId) }, {
            log.warn("order service error ${it.printStackTrace()}")
            emptyList()
        })

//        return orderServiceClient.getOrders(userId)
    }
}