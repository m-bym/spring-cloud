package com.mby.springcloud.gateway

import com.mby.springcloud.gateway.filter.UnAuthorizedException
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class ExceptionHandler: ErrorWebExceptionHandler {
    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        println("Exception Handler message : ${ex.message}")

        val response = exchange.response
        response.statusCode = when(ex.javaClass){
            UnAuthorizedException::class.java -> HttpStatus.UNAUTHORIZED
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }
        return response.setComplete()
    }
}