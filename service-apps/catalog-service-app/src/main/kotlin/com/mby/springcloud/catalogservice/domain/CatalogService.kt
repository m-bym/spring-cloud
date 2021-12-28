package com.mby.springcloud.catalogservice.domain

import com.mby.springcloud.catalogservice.dto.CatalogDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CatalogService(
    private val catalogRepository: CatalogRepository
) {

    @Transactional(readOnly = true)
    fun getCatalogById(id: Long): CatalogDto {
        val catalog =
            catalogRepository.findById(id).orElseThrow { NotFoundCatalogException("not found catalog id $id") }

        return CatalogDto.of(catalog)
    }

    @Transactional(readOnly = true)
    fun getCatalogByProductId(productId: String): CatalogDto {
        val catalog = catalogRepository.findByProductId(productId)
            ?: throw NotFoundCatalogException("not found catalog productId $productId")

        return CatalogDto.of(catalog)
    }

    @Transactional(readOnly = true)
    fun getAllCatalog(): List<CatalogDto> {
        return catalogRepository.findAll().map { CatalogDto.of(it) }
    }
}


class NotFoundCatalogException(override val message: String) : RuntimeException(message)