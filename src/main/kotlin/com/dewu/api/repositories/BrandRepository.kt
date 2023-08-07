package com.dewu.api.repositories

import com.dewu.api.entities.Brand
import com.dewu.api.entities.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BrandRepository : MongoRepository<Brand, String> {
    fun findByBrandId(brandId: Long): Brand?
    fun findByNameIgnoreCase(name: String): Brand?
    fun findAllBy(pageable: Pageable): Page<Brand>
    fun findAllByBrandIdIn(brandIds: List<Long>): List<Brand>
}