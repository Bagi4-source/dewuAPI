package com.dewu.api.dto

import com.dewu.api.entities.Sku
import lombok.Data

@Data
data class SkuPricesDTO(
        val skuId: Long,
        val prices: List<PriceDTO>
)
