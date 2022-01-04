package com.mby.springcloud.orderservice.messagequeue

import com.fasterxml.jackson.databind.ObjectMapper
import com.mby.springcloud.orderservice.dto.OrderDto
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {
    private val log = LoggerFactory.getLogger(javaClass)


    fun send(topic: String, orderDto: OrderDto) {
        val orderMessage = OrderMessage(
            id = orderDto.id,
            userId = orderDto.userId,
            catalogId = orderDto.catalogId,
            qty = orderDto.qty
        )

        try {
            val jsonInString = objectMapper.writeValueAsString(orderMessage)
            kafkaTemplate.send(topic, jsonInString)

            log.info("success, send kafka topic $topic message $jsonInString")
        } catch (ex: Exception) {
            log.error("fail, send kafka topic $topic message $orderMessage")
            ex.printStackTrace()
        }
    }
}