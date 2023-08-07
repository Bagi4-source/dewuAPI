package com.dewu.api.repositories

import com.dewu.api.entities.Sku
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SkuRepository : MongoRepository<Sku, String> {
    fun findBySkuId(skuId: Long): Sku?
    fun findAllBy(pageable: Pageable): Page<Sku>
}