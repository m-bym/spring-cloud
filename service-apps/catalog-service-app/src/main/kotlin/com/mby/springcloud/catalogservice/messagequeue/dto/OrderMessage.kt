package com.mby.springcloud.catalogservice.messagequeue.dto

data class OrderMessage(
    val id: Long,
    val userId: Long,
    val catalogId: Long,
    val qty: Int
)