package com.dewu.api.controller

import com.dewu.api.dto.CategoryDTO
import com.dewu.api.dto.PageDTO
import com.dewu.api.entities.Category
import com.dewu.api.repositories.CategoryRepository
import com.dewu.api.services.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/categories")
class CategoryController(@Autowired final val categoryRepository: CategoryRepository) {
    val categoryService = CategoryService(categoryRepository)

    @PostMapping
    fun createCategory(@RequestBody category: CategoryDTO): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.createCategory(category))
    }

    @PutMapping
    fun updateCategory(@RequestBody category: CategoryDTO): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.updateCategory(category))
    }

    @GetMapping("/{catId}")
    fun getCategoryById(@PathVariable catId: Long): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.getCategoryById(catId = catId))
    }

    @GetMapping
    fun getAllCategories(
            @RequestParam(defaultValue = "50") limit: Int,
            @RequestParam(defaultValue = "0") page: Int
    ): ResponseEntity<PageDTO<Category>> {
        return ResponseEntity.ok(PageDTO(categoryService.getAllCategories(limit, page)))
    }
}