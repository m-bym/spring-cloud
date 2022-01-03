package com.mby.springcloud.userservice.infra.dto

import java.time.ZonedDateTime

data class OrderPayload(
    val id: Long,
    val userId: Long,
    val catalogId: Long,
    val qty: Int,
    val unitPrice: Int,
    val totalPrice: Int,
    val createdAt: ZonedDateTime
)