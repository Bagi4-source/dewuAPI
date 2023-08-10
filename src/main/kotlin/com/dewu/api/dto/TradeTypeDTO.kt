package com.dewu.api.dto

import com.dewu.api.entities.TradeType
import lombok.Data

@Data
data class TradeTypeDTO(
        val tradeType: Long,
        val arrivalTimeText: String,
        val description: String,
        val maxTime: Int
) {
    constructor(tradeType: TradeType) : this(
            tradeType = tradeType.tradeType,
            arrivalTimeText = tradeType.arrivalTimeText,
            description = tradeType.description,
            maxTime = tradeType.maxTime
    )
}