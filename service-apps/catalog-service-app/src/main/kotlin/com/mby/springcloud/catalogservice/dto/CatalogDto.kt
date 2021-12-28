package com.mby.springcloud.catalogservice.dto

import com.mby.springcloud.catalogservice.domain.Catalog
import java.time.ZonedDateTime

data class CatalogDto(
    val id: Long,
    val productId: String,
    val productName: String,
    val stock: Int,
    val unitPrice: Int,
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
){
    companion object{
        fun of(catalog: Catalog): CatalogDto = CatalogDto(
            id = catalog.id,
            productId = catalog.productId,
            productName = catalog.productName,
            stock = catalog.stock,
            unitPrice = catalog.unitPrice,
            createdAt = catalog.createdAt
        )
    }
}
