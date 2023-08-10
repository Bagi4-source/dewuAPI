package com.dewu.api.controller

import com.dewu.api.dto.CategoryDTO
import com.dewu.api.dto.PageDTO
import com.dewu.api.entities.Category
import com.dewu.api.repositories.CategoryRepository
import com.dewu.api.services.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import kotlin.math.min


@RestController
@RequestMapping("/categories")
class CategoryController(@Autowired final val categoryRepository: CategoryRepository) {
    val categoryService = CategoryService(categoryRepository)

    @Value("\${getLimit}")
    private lateinit var getLimit: String

    @PostMapping
    fun createCategory(@RequestBody category: CategoryDTO): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.createCategory(category))
    }

    @PutMapping
    fun updateCategory(@RequestBody category: CategoryDTO): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.updateCategory(category))
    }

    @GetMapping("/{categoryId}")
    fun getCategoryById(@PathVariable categoryId: Long): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId = categoryId))
    }

    @PostMapping("/getCategories")
    fun getCategories(@RequestBody categoryIds: List<Long>): ResponseEntity<List<Category>> {
        if (categoryIds.size > getLimit.toInt()) throw ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE)
        return ResponseEntity.ok(categoryService.getCategoriesByIds(categoryIds))
    }

    @GetMapping
    fun getAllCategories(@RequestParam(defaultValue = "50") limit: Int, @RequestParam(defaultValue = "0") page: Int): ResponseEntity<PageDTO<Category>> {
        return ResponseEntity.ok(PageDTO(categoryService.getAllCategories(min(limit, getLimit.toInt()), page)))
    }
}