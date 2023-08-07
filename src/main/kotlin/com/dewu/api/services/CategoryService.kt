package com.dewu.api.services

import com.dewu.api.dto.CategoryDTO
import com.dewu.api.entities.Category
import com.dewu.api.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class CategoryService(
        @Autowired val categoryRepository: CategoryRepository
) {
    fun getCategoryById(catId: Long): Category {
        return categoryRepository.findByCatId(catId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getAllCategories(limit: Int, page: Int): Page<Category> {
        val pageable: Pageable = PageRequest.of(page, limit)
        return categoryRepository.findAllBy(pageable)
    }

    fun getCategoriesByIds(categoryIds: List<Long>): List<Category> {
        if (categoryIds.size > 100)
            throw ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE)
        return categoryRepository.findAllByCatIdIn(categoryIds)
    }

    fun updateCategory(category: CategoryDTO): Category {
        val instance = this.getCategoryById(catId = category.catId)
        instance.name = category.name
        return categoryRepository.save(instance)
    }

    fun createCategory(category: CategoryDTO): Category {
        try {
            this.getCategoryById(catId = category.catId)
        } catch (_: ResponseStatusException) {
            val instance = Category(
                    catId = category.catId,
                    name = category.name
            )
            return categoryRepository.insert(instance)
        }
        throw ResponseStatusException(HttpStatus.CONFLICT)
    }

}