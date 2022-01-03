package com.mby.springcloud.userservice.infra

import com.mby.springcloud.userservice.infra.dto.OrderPayload
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "order-service")
interface OrderServiceClient {
    @GetMapping("/order-service/{userId}/orders")
    fun getOrders(@PathVariable userId: Long): List<OrderPayload>
}