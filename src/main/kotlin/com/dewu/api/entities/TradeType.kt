package com.dewu.api.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.In
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document


@Document("trade_types")
data class TradeType(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val tradeType: Long,
        var arrivalTimeText: String,
        var description: String,
        var maxTime: Int
)
