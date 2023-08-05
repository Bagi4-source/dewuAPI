package com.dewu.api.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("products")
data class Product(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val spuId: Int,
        val title: String,
        val article: String,
        val images: List<String>,
        val categories: List<Category>,
        val brands: List<Brand>,
        val skus: List<Sku>,
        val detail: Any,
        val properties: Any,
        val info: Any,
)

@Document("categories")
data class Category(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val catId: Int,
        @Indexed(unique = true)
        val name: String
)

@Document("brands")
data class Brand(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val brandId: Int,
        @Indexed(unique = true)
        val name: String,
        val logo: String,
        val sizeGrid: Any
)

@Document("skus")
data class Sku(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val spuId: Int,

        val prices: List<Price>,
        val propertyId: Int,
        val propertyValueId: Int,
        val value: Int,
        val sort: String
)

data class Price(
        val tradeType: Int,
        val price: Int,
)

@Document("trade_types")
data class TradeType(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val tradeType: Int,
        val arrivalTimeText: String,
        val description: String,
        val maxTime: Int
)

@Document("properties")
data class Property(
        @Id
        val id: String = ObjectId().toString(),

        @Indexed(unique = true)
        val propertyId: Int,
        val name: String
)