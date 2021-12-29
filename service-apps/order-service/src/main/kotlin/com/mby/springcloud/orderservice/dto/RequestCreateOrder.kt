package com.mby.springcloud.orderservice.dto

data class RequestCreateOrder(
    val userId: Long,
    val catalogId: Long,
    val qty: Int
)
