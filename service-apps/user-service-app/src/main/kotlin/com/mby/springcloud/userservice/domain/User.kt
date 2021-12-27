package com.mby.springcloud.userservice.domain

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
    @Column
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column
    var updatedAt: ZonedDateTime = ZonedDateTime.now()
)