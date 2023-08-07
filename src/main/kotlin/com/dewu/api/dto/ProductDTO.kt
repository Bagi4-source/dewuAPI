package com.dewu.api.dto

import com.dewu.api.entities.Product
import lombok.Data

@Data
data class ProductDTO(
        val spuId: Long,
        val title: String,
        val article: String,
        val images: List<String>,
        val categories: List<Long>,
        val brands: List<Long>,
        val skus: List<Long>,
        val detail: Any,
        val properties: Any,
        val info: Any,
) {
    constructor(product: Product) : this(
            spuId = product.spuId,
            title = product.title,
            article = product.article,
            images = product.images,
            categories = product.categories,
            brands = product.brands,
            skus = product.skus,
            detail = product.detail,
            properties = product.properties,
            info = product.info
    )
}






