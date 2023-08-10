package com.dewu.api.services

import com.dewu.api.dto.BrandDTO
import com.dewu.api.entities.Brand
import com.dewu.api.repositories.BrandRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class BrandService(
        @Autowired val brandRepository: BrandRepository
) {
    fun getBrandById(brandId: Long): Brand {
        return brandRepository.findByBrandId(brandId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getBrandByName(brandName: String): Brand {
        return brandRepository.findByNameIgnoreCase(brandName) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getAllBrands(limit: Int, page: Int): Page<Brand> {
        val pageable: Pageable = PageRequest.of(page, limit)
        return brandRepository.findAllBy(pageable)
    }

    fun getBrandsByIds(brandIds: List<Long>): List<Brand> {
        return brandRepository.findAllByBrandIdIn(brandIds)
    }

    fun updateBrand(brand: BrandDTO): Brand {
        val instance = this.getBrandById(brandId = brand.brandId)
        instance.name = brand.name
        instance.logo = brand.logo
        instance.history = brand.history
        instance.sizeGrid = brand.sizeGrid
        return brandRepository.save(instance)
    }

    fun createBrand(brand: BrandDTO): Brand {
        try {
            this.getBrandById(brandId = brand.brandId)
            this.getBrandByName(brandName = brand.name)
        } catch (_: ResponseStatusException) {
            val instance = Brand(
                    brandId = brand.brandId,
                    name = brand.name,
                    logo = brand.logo,
                    history = brand.history,
                    sizeGrid = brand.sizeGrid
            )
            return brandRepository.insert(instance)
        }
        throw ResponseStatusException(HttpStatus.CONFLICT)
    }

}