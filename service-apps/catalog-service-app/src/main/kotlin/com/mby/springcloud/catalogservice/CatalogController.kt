package com.mby.springcloud.catalogservice

import com.mby.springcloud.catalogservice.domain.CatalogService
import com.mby.springcloud.catalogservice.dto.ResponseCatalog
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalog-service/catalogs")
class CatalogController(
    private val catalogService: CatalogService
) {

    @GetMapping
    fun getAllCatalogs(): ResponseEntity<List<ResponseCatalog>> {
        val catalogs = catalogService.getAllCatalog()
        return ResponseEntity.ok(
            catalogs.map { ResponseCatalog.of(it) }
        )
    }

    @GetMapping("/{catalogId}")
    fun getCatalogById(@PathVariable catalogId: Long): ResponseEntity<ResponseCatalog> {
        val catalog = catalogService.getCatalogById(catalogId)
        return ResponseEntity.ok(ResponseCatalog.of(catalog))
    }
}