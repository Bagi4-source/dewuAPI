package com.dewu.api.repositories

import com.dewu.api.entities.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<Product, String> {
    fun findBySpuId(spuId: Long): Product?
    fun findAllBy(pageable: Pageable): Page<Product>
}