package com.dewu.api.controller

import com.dewu.api.dto.PageDTO
import com.dewu.api.dto.ProductDTO
import com.dewu.api.entities.Product
import com.dewu.api.repositories.ProductRepository
import com.dewu.api.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import kotlin.math.min


@RestController
@RequestMapping("/products")
class ProductController(
        @Autowired final val productRepository: ProductRepository,
) {
    val productService = ProductService(productRepository)

    @Value("\${getLimit}")
    private lateinit var getLimit: String

    @PostMapping
    fun addProduct(@RequestBody product: ProductDTO): ResponseEntity<Product?> {
        return ResponseEntity.ok(productService.createProduct(product))
    }

    @PutMapping
    fun updateProduct(@RequestBody product: ProductDTO): ResponseEntity<Product?> {
        return ResponseEntity.ok(productService.updateProduct(product))
    }

    @GetMapping("/{spuId}")
    fun getProductBySpuId(@PathVariable("spuId") spuId: Long): ResponseEntity<Product> {
        return ResponseEntity.ok(productService.getProductById(spuId = spuId))
    }

    @PostMapping("/getProducts")
    fun getProducts(@RequestBody productIds: List<Long>): ResponseEntity<List<Product>> {
        if (productIds.size > getLimit.toInt()) throw ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE)
        return ResponseEntity.ok(productService.getProductsByIds(productIds))
    }

    @GetMapping
    fun getProducts(@RequestParam(defaultValue = "50") limit: Int, @RequestParam(defaultValue = "0") page: Int): ResponseEntity<PageDTO<Product>> {
        return ResponseEntity.ok(PageDTO(productService.getAllProducts(min(limit, getLimit.toInt()), page)))
    }
}