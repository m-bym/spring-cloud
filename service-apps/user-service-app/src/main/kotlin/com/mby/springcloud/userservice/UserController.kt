package com.mby.springcloud.userservice

import com.mby.springcloud.userservice.domain.UserService
import com.mby.springcloud.userservice.dto.RequestCreateUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping
    fun createUser(@RequestBody request: RequestCreateUser): ResponseEntity<Any> {
        userService.createUser(request)
        return ResponseEntity.ok(HttpStatus.CREATED)
    }
}