package com.mby.springcloud.userservice.dto

data class RequestCreateUser(
    val email: String,
    val pwd: String,
    val name: String
)
