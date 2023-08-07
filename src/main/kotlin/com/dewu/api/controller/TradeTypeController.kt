package com.dewu.api.controller

import com.dewu.api.dto.PageDTO
import com.dewu.api.dto.TradeTypeDTO
import com.dewu.api.entities.TradeType
import com.dewu.api.repositories.TradeTypeRepository
import com.dewu.api.services.TradeTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/tradeTypes")
class TradeTypeController(@Autowired final val tradeTypeRepository: TradeTypeRepository) {
    val tradeTypeService = TradeTypeService(tradeTypeRepository)

    @PostMapping
    fun createTradeType(@RequestBody tradeType: TradeTypeDTO): ResponseEntity<TradeType> {
        return ResponseEntity.ok(tradeTypeService.createTradeType(tradeType))
    }

    @PutMapping
    fun updateTradeType(@RequestBody tradeType: TradeTypeDTO): ResponseEntity<TradeType> {
        return ResponseEntity.ok(tradeTypeService.updateTradeType(tradeType))
    }

    @GetMapping("/{tradeType}")
    fun getTradeTypeById(@PathVariable tradeType: Int): ResponseEntity<TradeType> {
        return ResponseEntity.ok(tradeTypeService.getTradeTypeId(tradeType = tradeType))
    }

    @PostMapping("/getTradeTypes")
    fun getTradeTypes(
            @RequestBody tradeTypes: List<Int>
    ): ResponseEntity<List<TradeType>> {
        return ResponseEntity.ok(tradeTypeService.getTradeTypesByIds(tradeTypes))
    }

    @GetMapping
    fun getAllTradeTypes(
            @RequestParam(defaultValue = "50") limit: Int,
            @RequestParam(defaultValue = "0") page: Int
    ): ResponseEntity<PageDTO<TradeType>> {
        return ResponseEntity.ok(PageDTO(tradeTypeService.getAllTradeTypes(limit, page)))
    }
}