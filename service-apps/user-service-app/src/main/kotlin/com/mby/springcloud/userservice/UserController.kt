package com.mby.springcloud.userservice

import com.mby.springcloud.userservice.domain.UserService
import com.mby.springcloud.userservice.dto.RequestCreateUser
import com.mby.springcloud.userservice.dto.ResponseUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user-service/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping
    fun createUser(@RequestBody request: RequestCreateUser): ResponseEntity<Any> {
        userService.createUser(request)
        return ResponseEntity.ok(HttpStatus.CREATED)
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Long): ResponseEntity<ResponseUser> {
        val user = userService.getUser(userId)
        val userResponse = ResponseUser(
            id = user.id,
            name = user.name,
            email = user.email,
            orders = emptyList() //TODO
        )
        return ResponseEntity.ok(userResponse)
    }

    @GetMapping
    fun getAllUser(): ResponseEntity<List<ResponseUser>> {
        val users = userService.getAllUser()
        val userResponses = users.map { user ->
            ResponseUser(
                id = user.id,
                name = user.name,
                email = user.email,
                orders = emptyList() //TODO
            )
        }

        return ResponseEntity.ok(userResponses)
    }
}