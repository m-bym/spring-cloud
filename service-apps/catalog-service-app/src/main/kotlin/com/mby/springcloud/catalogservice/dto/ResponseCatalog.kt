package com.mby.springcloud.catalogservice.dto

data class ResponseCatalog(
    val id: Long,
    val productId: String,
    val productName: String,
    val stock: Int,
    val unitPrice: Int,
) {
    companion object {
        fun of(catalog: CatalogDto): ResponseCatalog = ResponseCatalog(
            id = catalog.id,
            productId = catalog.productId,
            productName = catalog.productName,
            stock = catalog.stock,
            unitPrice = catalog.unitPrice
        )
    }
}
