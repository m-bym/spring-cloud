package com.mby.springcloud.userservice.domain

import com.mby.springcloud.userservice.dto.RequestCreateUser
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional
    fun createUser(request: RequestCreateUser) {
        userRepository.save(
            User(
                email = request.email,
                name = request.name,
                encryptedPwd = "encrypted${request.pwd}"
            )
        )
    }
}