package com.mby.springcloud.userservice

import com.mby.springcloud.userservice.domain.UserService
import com.mby.springcloud.userservice.dto.RequestCreateUser
import com.mby.springcloud.userservice.dto.ResponseUser
import com.mby.springcloud.userservice.dto.ResponseUserOrder
import com.mby.springcloud.userservice.infra.UserOrderClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val userOrderClient: UserOrderClient
) {
    @PostMapping
    fun createUser(@RequestBody request: RequestCreateUser): ResponseEntity<ResponseUser> {
        val user = userService.createUser(request)
        val userResponse = ResponseUser(
            id = user.id,
            name = user.name,
            email = user.email,
            orders = emptyList()
        )
        return ResponseEntity.ok(userResponse)
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Long): ResponseEntity<ResponseUser> {
        val user = userService.getUser(userId)
        val userOrders = userOrderClient.getUserOrders(userId)

        val userResponse = ResponseUser(
            id = user.id,
            name = user.name,
            email = user.email,
            orders = userOrders.map {
                ResponseUserOrder(
                    id = it.id,
                    catalogId = it.catalogId,
                    qty = it.qty,
                    unitPrice = it.unitPrice,
                    totalPrice = it.totalPrice
                )
            }
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