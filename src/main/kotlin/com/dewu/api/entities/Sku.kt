package com.dewu.api.entities

import com.dewu.api.dto.PriceDTO
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document


@Document("skus")
data class Sku(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val skuId: Long,

        var prices: List<PriceDTO>,
        var propertyId: Long,
        var value: String,
        var sort: Int
)
