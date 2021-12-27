package com.mby.springcloud.userservice.dto

data class ResponseUser(
    val id: Long,
    val name: String,
    val email: String,
    val orders: List<ResponseUserOrder> = emptyList()
)

data class ResponseUserOrder(
    val id: Long,
    val qty: Int,
    val unitPrice: Int,
    val totalPrice: Int
)