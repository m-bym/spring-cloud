package com.mby.springcloud.orderservice.domain

import com.mby.springcloud.orderservice.dto.OrderDto
import com.mby.springcloud.orderservice.dto.RequestCreateOrder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    @Transactional(readOnly = true)
    fun getAllOrder(): List<OrderDto> {
        return orderRepository.findAll().map(OrderDto::of)
    }

    @Transactional(readOnly = true)
    fun getOrderById(id: Long): OrderDto {
        val order = orderRepository.findById(id).orElseThrow { NotFoundOrderException("not found order id $id") }
        return OrderDto.of(order)
    }

    @Transactional(readOnly = true)
    fun getOrdersByUserId(userId: Long): List<OrderDto> {
        return orderRepository.findByUserId(userId).map(OrderDto::of)
    }

    @Transactional
    fun createOrder(userId: Long, request: RequestCreateOrder) {
        //TODO : Validation -> userId, catalogId, qty
        val order = Order(
            userId = userId,
            catalogId = request.catalogId,
            qty = request.qty
        )
        orderRepository.save(order)
    }
}

class NotFoundOrderException(override val message: String) : RuntimeException(message)