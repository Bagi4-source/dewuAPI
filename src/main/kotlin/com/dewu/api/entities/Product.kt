package com.dewu.api.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("products")
data class Product(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val spuId: Long,
        var title: String,
        var article: String,
        var images: List<String>,
        var categories: List<Long>,
        var brands: List<Long>,
        var skus: List<Long>,
        var detail: Any,
        var properties: Any,
        var info: Any,
)