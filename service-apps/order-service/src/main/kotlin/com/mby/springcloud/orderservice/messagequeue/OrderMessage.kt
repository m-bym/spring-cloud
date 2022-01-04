package com.mby.springcloud.orderservice.messagequeue

data class OrderMessage(
    val id: Long,
    val userId: Long,
    val catalogId: Long,
    val qty: Int
)