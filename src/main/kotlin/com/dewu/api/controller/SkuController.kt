package com.dewu.api.controller

import com.dewu.api.dto.PageDTO
import com.dewu.api.dto.SkuDTO
import com.dewu.api.dto.SkuPricesDTO
import com.dewu.api.entities.Sku
import com.dewu.api.repositories.SkuRepository
import com.dewu.api.services.SkuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import kotlin.math.min


@RestController
@RequestMapping("/skus")
class SkuController(@Autowired final val skuRepository: SkuRepository) {
    val skuService = SkuService(skuRepository)

    @Value("\${getLimit}")
    private lateinit var getLimit: String

    @PostMapping
    fun createSkus(@RequestBody skus: List<SkuDTO>): ResponseEntity<List<Sku>> {
        if (skus.size > getLimit.toInt()) throw ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE)
        return ResponseEntity.ok(skuService.createSkus(skus))
    }

    @PutMapping
    fun updateSkus(@RequestBody skus: List<SkuDTO>): ResponseEntity<List<Sku>> {
        if (skus.size > getLimit.toInt()) throw ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE)
        return ResponseEntity.ok(skuService.updateSkus(skus))
    }

    @GetMapping("/{skuId}")
    fun getSkuById(@PathVariable skuId: Long): ResponseEntity<Sku> {
        return ResponseEntity.ok(skuService.getSkuById(skuId = skuId))
    }

    @PostMapping("/getSkus")
    fun getSkus(@RequestBody skuIds: List<Long>): ResponseEntity<List<Sku>> {
        if (skuIds.size > getLimit.toInt()) throw ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE)
        return ResponseEntity.ok(skuService.getSkusByIds(skuIds))
    }

    @PostMapping("/updatePrices")
    fun updatePrices(@RequestBody skus: List<SkuPricesDTO>): ResponseEntity<List<Sku>> {
        if (skus.size > getLimit.toInt()) throw ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE)
        return ResponseEntity.ok(skuService.updatePrices(skus))
    }

    @GetMapping
    fun getAllSkus(@RequestParam(defaultValue = "50") limit: Int, @RequestParam(defaultValue = "0") page: Int): ResponseEntity<PageDTO<Sku>> {
        return ResponseEntity.ok(PageDTO(skuService.getAllSkus(min(limit, getLimit.toInt()), page)))
    }
}