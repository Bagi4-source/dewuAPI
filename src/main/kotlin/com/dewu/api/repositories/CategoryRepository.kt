package com.dewu.api.repositories

import com.dewu.api.entities.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : MongoRepository<Category, String> {
    fun findByCategoryId(catId: Long): Category?
    fun findAllBy(pageable: Pageable): Page<Category>
    fun findAllByCategoryIdIn(catIds: List<Long>): List<Category>
}