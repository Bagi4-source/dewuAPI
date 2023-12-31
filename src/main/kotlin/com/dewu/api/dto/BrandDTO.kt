package com.dewu.api.dto

import com.dewu.api.entities.Brand
import lombok.Data

@Data
data class BrandDTO(
        val brandId: Long,
        val name: String,
        val logo: String,
        val history: String,
        val sizeGrid: Any
) {
    constructor(brand: Brand) : this(
            brandId = brand.brandId,
            name = brand.name,
            logo = brand.logo,
            history = brand.history,
            sizeGrid = brand.sizeGrid
    )
}
