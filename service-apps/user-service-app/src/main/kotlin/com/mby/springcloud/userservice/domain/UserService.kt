package com.mby.springcloud.userservice.domain

import com.mby.springcloud.userservice.dto.RequestCreateUser
import com.mby.springcloud.userservice.dto.UserDto
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

    @Transactional(readOnly = true)
    fun getUser(id: Long): UserDto {
        val user = userRepository.findById(id).orElseThrow { NotFoundUserException("not found user id $id") }
        return UserDto.of(user)
    }

    @Transactional(readOnly = true)
    fun getAllUser(): List<UserDto> {
        val users = userRepository.findAll()
        return users.map { UserDto.of(it) }
    }

}

class NotFoundUserException(override val message: String) : RuntimeException(message)