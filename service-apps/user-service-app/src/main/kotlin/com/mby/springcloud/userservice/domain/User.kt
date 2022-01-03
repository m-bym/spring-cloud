package com.mby.springcloud.userservice.domain

import org.hibernate.annotations.ColumnDefault
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(nullable = false, length = 50, unique = true)
    val email: String,
    @Column(nullable = false, length = 50)
    var name: String,
    @Column(nullable = false)
    var encryptedPwd: String,
    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    var updatedAt: ZonedDateTime = ZonedDateTime.now()
)