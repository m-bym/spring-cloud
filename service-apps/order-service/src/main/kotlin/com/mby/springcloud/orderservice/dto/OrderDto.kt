package com.mby.springcloud.orderservice.dto

import com.mby.springcloud.orderservice.domain.Order
import java.time.ZonedDateTime

data class OrderDto(
    val id: Long,
    val userId: Long,
    val catalogId: Long,
    val qty: Int,
    val unitPrice: Int,
    val totalPrice: Int,
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
) {
    companion object {
        fun of(order: Order): OrderDto = OrderDto(
            id = order.id,
            userId = order.userId,
            catalogId = order.catalogId,
            unitPrice = order.unitPrice,
            totalPrice = order.totalPrice,
            qty = order.qty,
            createdAt = order.createdAt
        )
    }
}
