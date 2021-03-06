package com.mby.springcloud.orderservice.domain

import com.mby.springcloud.orderservice.dto.OrderDto
import com.mby.springcloud.orderservice.dto.RequestCreateOrder
import com.mby.springcloud.orderservice.messagequeue.KafkaProducer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val kafkaProducer: KafkaProducer
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
    fun createOrder(userId: Long, request: RequestCreateOrder): OrderDto {
        //TODO : Validation -> userId, catalogId, qty
        val order = Order(
            userId = userId,
            catalogId = request.catalogId,
            qty = request.qty,
            unitPrice = request.unitPrice,
            totalPrice = request.totalPrice
        )

        val savedOrderDto = OrderDto.of(orderRepository.save(order))
        kafkaProducer.send("catalog-topic", savedOrderDto)

        return savedOrderDto
    }
}

class NotFoundOrderException(override val message: String) : RuntimeException(message)