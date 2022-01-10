package com.mby.springcloud.userservice.infra

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration


@Configuration
class Resilience4JConfig {
    @Bean
    fun defaultCustomizer(): Customizer<Resilience4JCircuitBreakerFactory>? {
        return Customizer { factory: Resilience4JCircuitBreakerFactory ->
            factory.configureDefault { id: String ->
                Resilience4JConfigBuilder(id)
                    .timeLimiterConfig(
                        TimeLimiterConfig.custom()
                            .timeoutDuration(Duration.ofSeconds(3))
                            .build()
                    )
                    .circuitBreakerConfig(
                        CircuitBreakerConfig.custom()
                            .failureRateThreshold(3F)
                            .waitDurationInOpenState(Duration.ofMillis(1000))
                            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                            .slidingWindowSize(2)
                            .build()
                    ).build()
            }
        }
    }
}