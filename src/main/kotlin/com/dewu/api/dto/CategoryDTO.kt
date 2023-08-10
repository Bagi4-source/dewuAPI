package com.dewu.api.dto

import com.dewu.api.entities.Category
import lombok.Data

@Data
data class CategoryDTO(
        val categoryId: Long,
        val name: String,
) {
    constructor(category: Category) : this(
            categoryId = category.categoryId,
            name = category.name
    )
}