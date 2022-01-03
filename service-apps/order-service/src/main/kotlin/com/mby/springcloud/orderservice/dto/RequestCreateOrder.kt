package com.mby.springcloud.orderservice.dto

data class RequestCreateOrder(
    val catalogId: Long,
    val qty: Int,
    val unitPrice: Int,
    val totalPrice: Int
)
