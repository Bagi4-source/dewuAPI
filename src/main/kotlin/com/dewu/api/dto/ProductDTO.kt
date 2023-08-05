package com.dewu.api.dto

import lombok.Data

@Data
data class ProductDTO(
        var spuId: Int,
        var price: Int
)