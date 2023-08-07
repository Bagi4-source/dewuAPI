package com.dewu.api.dto

import com.dewu.api.entities.Category
import lombok.Data

@Data
data class CategoryDTO(
        val catId: Long,
        val name: String,
) {
    constructor(category: Category) : this(
            catId = category.catId,
            name = category.name
    )
}