package com.mby.springcloud.userservice.dto

import com.mby.springcloud.userservice.domain.User
import java.time.ZonedDateTime

data class UserDto(
    val id: Long,
    val email: String,
    val name: String,
    val encryptedPwd: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime
) {
    companion object {
        fun of(user: User): UserDto {
            return UserDto(
                id = user.id,
                email = user.email,
                name = user.name,
                encryptedPwd = user.encryptedPwd,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt
            )
        }
    }
}
