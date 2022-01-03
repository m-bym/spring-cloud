package com.mby.springcloud.userservice.infra

import com.mby.springcloud.userservice.infra.dto.OrderPayload
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class UserOrderClient(
    //private val restTemplate: RestTemplate,
    private val orderServiceClient: OrderServiceClient,
    private val env: Environment
) {

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

        /* Using a feign client */
        return orderServiceClient.getOrders(userId)
    }
}