package com.dewu.api.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document


@Document("brands")
data class Brand(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val brandId: Long,
        @Indexed(unique = true)
        var name: String,
        var logo: String,
        var history: String,
        var sizeGrid: Any
)
