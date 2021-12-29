package com.mby.springcloud.orderservice

import com.mby.springcloud.orderservice.domain.OrderService
import com.mby.springcloud.orderservice.dto.RequestCreateOrder
import com.mby.springcloud.orderservice.dto.ResponseOrder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order-service/")
class UserOrderController(
    private val orderService: OrderService
) {
    @GetMapping("{userId}/orders")
    fun getUserOrders(@PathVariable userId: Long): List<ResponseOrder> {
        return orderService.getOrdersByUserId(userId).map(ResponseOrder::of)
    }

    @PostMapping("/{userId}/orders")
    fun createUserOrder(@PathVariable userId: Long,@RequestBody request: RequestCreateOrder): ResponseEntity<Any> {
        orderService.createOrder(userId,request)
        return ResponseEntity.ok(HttpStatus.CREATED)
    }
}