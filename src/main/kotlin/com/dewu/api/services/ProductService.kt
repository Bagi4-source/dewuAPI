package com.dewu.api.services

import com.dewu.api.dto.ProductDTO
import com.dewu.api.entities.Product
import com.dewu.api.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ProductService(
        @Autowired val productRepository: ProductRepository
) {
    fun getProductById(spuId: Long): Product {
        return productRepository.findBySpuId(spuId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getAllProducts(limit: Int, page: Int): Page<Product> {
        val pageable: Pageable = PageRequest.of(page, limit)
        return productRepository.findAllBy(pageable)
    }

    fun getProductsByIds(productIds: List<Long>): List<Product> {
        return productRepository.findAllBySpuIdIn(productIds)
    }

    fun updateProduct(product: ProductDTO): Product {
        val instance = this.getProductById(spuId = product.spuId)
        instance.title = product.title
        instance.description = product.description
        instance.article = product.article
        instance.sellDate = product.sellDate
        instance.images = product.images
        instance.categories = product.categories
        instance.brands = product.brands
        instance.skus = product.skus
        instance.properties = product.properties
        return productRepository.save(instance)
    }

    fun createProduct(product: ProductDTO): Product {
        try {
            this.getProductById(spuId = product.spuId)
        } catch (_: ResponseStatusException) {
            val instance = Product(
                    spuId = product.spuId,
                    title = product.title,
                    description = product.description,
                    article = product.article,
                    sellDate = product.sellDate,
                    images = product.images,
                    categories = product.categories,
                    brands = product.brands,
                    skus = product.skus,
                    properties = product.properties,
            )
            return productRepository.insert(instance)
        }
        throw ResponseStatusException(HttpStatus.CONFLICT)
    }

}