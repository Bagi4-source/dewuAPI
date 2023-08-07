package com.dewu.api.services

import com.dewu.api.dto.SkuDTO
import com.dewu.api.entities.Sku
import com.dewu.api.repositories.SkuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class SkuService(
        @Autowired val skuRepository: SkuRepository
) {
    fun getSkuById(skuId: Long): Sku {
        return skuRepository.findBySkuId(skuId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getAllSkus(limit: Int, page: Int): Page<Sku> {
        val pageable: Pageable = PageRequest.of(page, limit)
        return skuRepository.findAllBy(pageable)
    }

    fun getSkusByIds(skuIds: List<Long>): List<Sku> {
        if (skuIds.size > 100)
            throw ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE)
        return skuRepository.findAllBySkuIdIn(skuIds)
    }

    fun updateSku(sku: SkuDTO): Sku {
        val instance = this.getSkuById(skuId = sku.skuId)
        instance.prices = sku.prices
        instance.propertyId = sku.propertyId
        instance.value = sku.value
        instance.sort = sku.sort
        return skuRepository.save(instance)
    }

    fun createSku(sku: SkuDTO): Sku {
        try {
            this.getSkuById(skuId = sku.skuId)
        } catch (_: ResponseStatusException) {
            val instance = Sku(
                    skuId = sku.skuId,
                    prices = sku.prices,
                    propertyId = sku.propertyId,
                    value = sku.value,
                    sort = sku.sort
            )
            return skuRepository.insert(instance)
        }
        throw ResponseStatusException(HttpStatus.CONFLICT)
    }

}