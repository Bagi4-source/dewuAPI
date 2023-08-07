package com.dewu.api.services

import com.dewu.api.dto.TradeTypeDTO
import com.dewu.api.entities.TradeType
import com.dewu.api.repositories.TradeTypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class TradeTypeService(
        @Autowired val tradeTypeRepository: TradeTypeRepository
) {
    fun getTradeTypeId(tradeType: Int): TradeType {
        return tradeTypeRepository.findByTradeType(tradeType) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getAllTradeTypes(limit: Int, page: Int): Page<TradeType> {
        val pageable: Pageable = PageRequest.of(page, limit)
        return tradeTypeRepository.findAllBy(pageable)
    }

    fun updateTradeType(tradeType: TradeTypeDTO): TradeType {
        val instance = this.getTradeTypeId(tradeType = tradeType.tradeType)
        instance.arrivalTimeText = tradeType.arrivalTimeText
        instance.description = tradeType.description
        instance.maxTime = tradeType.maxTime
        return tradeTypeRepository.save(instance)
    }

    fun createTradeType(tradeType: TradeTypeDTO): TradeType {
        try {
            this.getTradeTypeId(tradeType = tradeType.tradeType)
        } catch (_: ResponseStatusException) {
            val instance = TradeType(
                    tradeType = tradeType.tradeType,
                    arrivalTimeText = tradeType.arrivalTimeText,
                    description = tradeType.description,
                    maxTime = tradeType.maxTime
            )
            return tradeTypeRepository.insert(instance)
        }
        throw ResponseStatusException(HttpStatus.CONFLICT)
    }

}