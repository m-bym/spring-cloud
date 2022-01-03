package com.mby.springcloud.userservice.infra

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class FeignErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception {
        return ResponseStatusException(
            HttpStatus.valueOf(response.status()),
            "feign client request error, methodKey $methodKey"
        )
    }
}