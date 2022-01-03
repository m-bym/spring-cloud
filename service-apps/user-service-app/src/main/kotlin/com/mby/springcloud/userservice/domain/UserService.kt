package com.mby.springcloud.userservice.domain

import com.mby.springcloud.userservice.dto.RequestCreateUser
import com.mby.springcloud.userservice.dto.UserDto
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {
    @Transactional
    fun createUser(request: RequestCreateUser): UserDto {
        return UserDto.of(
            userRepository.save(
                User(
                    email = request.email,
                    name = request.name,
                    encryptedPwd = passwordEncoder.encode(request.pwd)
                )
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

    @Transactional(readOnly = true)
    fun getUserByEmail(email: String): UserDto {
        val user = userRepository.findByEmail(email) ?: throw NotFoundUserException("not found user email $email")
        return UserDto.of(user)
    }

}

class NotFoundUserException(override val message: String) : RuntimeException(message)