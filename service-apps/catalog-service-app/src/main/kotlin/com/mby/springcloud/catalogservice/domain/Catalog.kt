package com.mby.springcloud.catalogservice.domain

import org.hibernate.annotations.ColumnDefault
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "catalogs")
class Catalog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(nullable = false, length = 120, unique = true)
    val productId: String,
    @Column(nullable = false)
    val productName: String,
    @Column(nullable = false)
    var stock: Int,
    @Column(nullable = false)
    val unitPrice: Int,
    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
) {
    fun decreaseStock(qty: Int) {
        stock -= qty
    }
}
