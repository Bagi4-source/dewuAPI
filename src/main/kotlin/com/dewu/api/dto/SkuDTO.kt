package com.dewu.api.dto

import com.dewu.api.entities.Sku
import lombok.Data

@Data
data class SkuDTO(
        val spuId: Long,
        val skuId: Long,
        val prices: List<PriceDTO>,
        val propertyId: Long,
        val value: String,
        val sort: Int
){
    constructor(sku: Sku) : this(
            spuId = sku.spuId,
            skuId = sku.skuId,
            prices = sku.prices,
            propertyId = sku.propertyId,
            value = sku.value,
            sort = sku.sort,
    )
}