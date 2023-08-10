package com.dewu.api.dto

import lombok.Data

@Data
data class PriceDTO(
        val tradeType: Long,
        val price: Int,
)