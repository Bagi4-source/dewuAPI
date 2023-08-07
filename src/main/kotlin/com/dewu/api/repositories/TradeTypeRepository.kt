package com.dewu.api.repositories

import com.dewu.api.entities.Product
import com.dewu.api.entities.TradeType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TradeTypeRepository : MongoRepository<TradeType, String> {
    fun findByTradeType(tradeType: Int): TradeType?
    fun findAllBy(pageable: Pageable): Page<TradeType>

    fun findAllByTradeTypeIn(tradeTypes: List<Int>): List<TradeType>

}