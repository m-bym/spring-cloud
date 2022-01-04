package com.mby.springcloud.orderservice

import com.mby.springcloud.orderservice.domain.OrderService
import com.mby.springcloud.orderservice.dto.ResponseOrder
import com.mby.springcloud.orderservice.messagequeue.KafkaProducer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order-service/")
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping("/orders")
    fun getAllOrders(): ResponseEntity<List<ResponseOrder>> {
        val orders = orderService.getAllOrder()
        return ResponseEntity.ok(orders.map(ResponseOrder::of))
    }

    @GetMapping("/orders/{orderId}")
    fun getOrderById(@PathVariable orderId: Long): ResponseEntity<ResponseOrder> {
        val order = orderService.getOrderById(orderId)
        return ResponseEntity.ok(ResponseOrder.of(order))
    }
}