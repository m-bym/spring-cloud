package com.mby.springcloud.catalogservice.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CatalogRepository : JpaRepository<Catalog, Long> {
    fun findByProductId(productId: String): Catalog?
}