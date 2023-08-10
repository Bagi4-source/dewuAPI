package com.dewu.api.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("categories")
data class Category(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val categoryId: Long,
        var name: String
)