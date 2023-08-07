package com.dewu.api.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document


@Document("properties")
data class Property(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val propertyId: Long,
        val name: String
)
