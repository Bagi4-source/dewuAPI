package com.dewu.api.controller

import com.dewu.api.dto.BrandDTO
import com.dewu.api.dto.PageDTO
import com.dewu.api.entities.Brand
import com.dewu.api.repositories.BrandRepository
import com.dewu.api.services.BrandService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/brands")
class BrandController(@Autowired final val brandRepository: BrandRepository) {
    val brandService = BrandService(brandRepository)

    @PostMapping
    fun createBrand(@RequestBody brand: BrandDTO): ResponseEntity<Brand> {
        return ResponseEntity.ok(brandService.createBrand(brand))
    }

    @PutMapping
    fun updateBrand(@RequestBody brand: BrandDTO): ResponseEntity<Brand> {
        return ResponseEntity.ok(brandService.updateBrand(brand))
    }

    @GetMapping("/getById/{brandId}")
    fun getBrandById(@PathVariable brandId: Long): ResponseEntity<Brand> {
        return ResponseEntity.ok(brandService.getBrandById(brandId = brandId))
    }

    @GetMapping("/getByName/{brandName}")
    fun getBrandByName(@PathVariable brandName: String): ResponseEntity<Brand> {
        return ResponseEntity.ok(brandService.getBrandByName(brandName = brandName))
    }

    @PostMapping("/getBrands")
    fun getBrands(
            @RequestBody brandIds: List<Long>
    ): ResponseEntity<List<Brand>> {
        return ResponseEntity.ok(brandService.getBrandsByIds(brandIds))
    }

    @GetMapping
    fun getAllBrands(
            brandId: Long?,
            brandName: String?,
            @RequestParam(defaultValue = "50") limit: Int,
            @RequestParam(defaultValue = "0") page: Int
    ): ResponseEntity<out Any> {
        if (brandId != null)
            return this.getBrandById(brandId)
        if (brandName != null)
            return this.getBrandByName(brandName)
        return ResponseEntity.ok(PageDTO(brandService.getAllBrands(limit, page)))
    }
}