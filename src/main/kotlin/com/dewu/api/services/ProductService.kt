package com.dewu.api.services

import com.dewu.api.models.Product
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductService : MongoRepository<Product, String> {
    fun findBySpuId(spuId: Int): Product?
}