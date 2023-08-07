package com.dewu.api.controller

import com.dewu.api.dto.PageDTO
import com.dewu.api.dto.SkuDTO
import com.dewu.api.entities.Sku
import com.dewu.api.repositories.SkuRepository
import com.dewu.api.services.SkuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/skus")
class SkuController(@Autowired final val skuRepository: SkuRepository) {
    val skuService = SkuService(skuRepository)

    @PostMapping
    fun createSku(@RequestBody sku: SkuDTO): ResponseEntity<Sku> {
        return ResponseEntity.ok(skuService.createSku(sku))
    }

    @PutMapping
    fun updateSku(@RequestBody sku: SkuDTO): ResponseEntity<Sku> {
        return ResponseEntity.ok(skuService.updateSku(sku))
    }

    @GetMapping("/{skuId}")
    fun getSkuById(@PathVariable skuId: Long): ResponseEntity<Sku> {
        return ResponseEntity.ok(skuService.getSkuById(skuId = skuId))
    }

    @GetMapping
    fun getAllSkus(
            @RequestParam(defaultValue = "50") limit: Int,
            @RequestParam(defaultValue = "0") page: Int
    ): ResponseEntity<PageDTO<Sku>> {
        return ResponseEntity.ok(PageDTO(skuService.getAllSkus(limit, page)))
    }
}