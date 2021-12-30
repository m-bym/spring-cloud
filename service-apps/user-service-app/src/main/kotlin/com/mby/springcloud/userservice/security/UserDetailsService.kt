package com.mby.springcloud.userservice.security

import com.mby.springcloud.userservice.domain.NotFoundUserException
import com.mby.springcloud.userservice.domain.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username) ?: throw NotFoundUserException("not found user email $username")
        return User(user.email, user.encryptedPwd, true, true, true, true, listOf())
    }
}