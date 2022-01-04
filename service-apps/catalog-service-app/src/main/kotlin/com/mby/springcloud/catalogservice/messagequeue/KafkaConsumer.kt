package com.mby.springcloud.catalogservice.messagequeue

import com.fasterxml.jackson.databind.ObjectMapper
import com.mby.springcloud.catalogservice.domain.CatalogRepository
import com.mby.springcloud.catalogservice.domain.NotFoundCatalogException
import com.mby.springcloud.catalogservice.messagequeue.dto.OrderMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class KafkaConsumer(
    private val catalogRepository: CatalogRepository,
    private val objectMapper: ObjectMapper
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["catalog-topic"])
    @Transactional
    fun updateQty(kafkaMessage: String) {
        log.info("Kafka Message -> $kafkaMessage")
        try {
            val orderMessage = objectMapper.readValue(kafkaMessage, OrderMessage::class.java)
            val catalog = catalogRepository.findById(orderMessage.catalogId).orElseThrow {
                NotFoundCatalogException("not found catalog id ${orderMessage.catalogId}")
            }

            catalog.decreaseStock(orderMessage.qty)
            catalogRepository.save(catalog)
        } catch (ex: Exception) {
            log.error("fail update catalog qty, message : $kafkaMessage")
            ex.printStackTrace()
        }
    }
}