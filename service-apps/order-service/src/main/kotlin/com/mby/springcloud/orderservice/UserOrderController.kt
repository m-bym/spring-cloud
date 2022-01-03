package com.mby.springcloud.orderservice

import com.mby.springcloud.orderservice.domain.OrderService
import com.mby.springcloud.orderservice.dto.RequestCreateOrder
import com.mby.springcloud.orderservice.dto.ResponseOrder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order-service/")
class UserOrderController(
    private val orderService: OrderService
) {
    @GetMapping("{userId}/orders")
    fun getUserOrders(@PathVariable userId: Long): ResponseEntity<List<ResponseOrder>> {
        val orders = orderService.getOrdersByUserId(userId).map(ResponseOrder::of)
        return ResponseEntity.ok().body(orders)
    }

    @PostMapping("/{userId}/orders")
    fun createUserOrder(
        @PathVariable userId: Long,
        @RequestBody request: RequestCreateOrder
    ): ResponseEntity<ResponseOrder> {
        val order = orderService.createOrder(userId, request)
        val orderResponse = ResponseOrder.of(order)
        return ResponseEntity.ok().body(orderResponse)
    }
}