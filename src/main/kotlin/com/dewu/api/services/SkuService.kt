package com.dewu.api.services

import com.dewu.api.dto.SkuDTO
import com.dewu.api.dto.SkuPricesDTO
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
        return skuRepository.findAllBySkuIdIn(skuIds)
    }

    private fun updateSku(sku: SkuDTO): Sku {
        val instance = this.getSkuById(skuId = sku.skuId)
        instance.prices = sku.prices
        instance.propertyId = sku.propertyId
        instance.value = sku.value
        instance.sort = sku.sort
        return skuRepository.save(instance)
    }

    private fun updatePrice(sku: SkuPricesDTO): Sku {
        val instance = this.getSkuById(skuId = sku.skuId)
        instance.prices = sku.prices
        return skuRepository.save(instance)
    }

    fun updatePrices(skus: List<SkuPricesDTO>): List<Sku> {
        val result: MutableList<Sku> = mutableListOf()
        skus.forEach {
            try {
                result.add(this.updatePrice(it))
            } catch (_: ResponseStatusException) {

            }
        }
        return result
    }

    fun updateSkus(skus: List<SkuDTO>): List<Sku> {
        val result: MutableList<Sku> = mutableListOf()
        skus.forEach {
            try {
                result.add(this.updateSku(it))
            } catch (_: ResponseStatusException) {

            }
        }
        return result
    }

    private fun createSku(sku: SkuDTO): Sku {
        try {
            this.getSkuById(skuId = sku.skuId)
        } catch (_: ResponseStatusException) {
            val instance = Sku(
                    spuId = sku.spuId,
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

    fun createSkus(skus: List<SkuDTO>): List<Sku> {
        val result: MutableList<Sku> = mutableListOf()
        skus.forEach {
            try {
                result.add(this.createSku(it))
            } catch (_: ResponseStatusException) {

            }
        }
        return result
    }

}