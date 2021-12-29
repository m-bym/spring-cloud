package com.mby.springcloud.orderservice.dto

import java.time.ZonedDateTime

data class ResponseOrder(
    val id: Long,
    val userId: Long,
    val catalogId: Long,
    val qty: Int,
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
) {
    companion object {
        fun of(order: OrderDto): ResponseOrder = ResponseOrder(
            id = order.id,
            userId = order.userId,
            catalogId = order.catalogId,
            qty = order.qty,
            createdAt = order.createdAt
        )
    }
}