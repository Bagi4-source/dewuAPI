package com.dewu.api.dto

import com.dewu.api.entities.Product
import lombok.Data

@Data
data class ProductDTO(
        val spuId: Long,
        val title: String,
        val description: String,
        val article: String,
        val sellDate: String,
        val images: List<String>,
        val categories: List<Long>,
        val brands: List<Long>,
        val skus: List<Long>,
        val properties: Any,
) {
    constructor(product: Product) : this(
            spuId = product.spuId,
            title = product.title,
            description = product.description,
            article = product.article,
            sellDate = product.sellDate,
            images = product.images,
            categories = product.categories,
            brands = product.brands,
            skus = product.skus,
            properties = product.properties,
    )
}






