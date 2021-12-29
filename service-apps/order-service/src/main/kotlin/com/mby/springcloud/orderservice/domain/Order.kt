package com.mby.springcloud.orderservice.domain

import org.hibernate.annotations.ColumnDefault
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(nullable = false)
    val userId: Long,
    @Column(nullable = false)
    val catalogId: Long,
    @Column(nullable = false)
    val qty: Int,
    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
)