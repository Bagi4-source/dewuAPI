package com.dewu.api.controller

import com.dewu.api.dto.ProductDTO
import com.dewu.api.models.Product
import com.dewu.api.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/products")
class ProductController(@Autowired val productService: ProductService) {
    @PostMapping
    fun addProduct(@RequestBody product: ProductDTO): Product? {
        val instance = Product(
                spuId = product.spuId,
                price = product.price
        )
        return productService.insert(instance)
    }

    @GetMapping
    fun getProducts(): List<Product>? {
        return productService.findAll()
    }

    @GetMapping("/{spuId}")
    fun getProductBySpuId(@PathVariable("spuId") spuId: Int): ResponseEntity<Product> {
        val product = productService.findBySpuId(spuId)
        return if (product != null) ResponseEntity.ok(product) else ResponseEntity
                .notFound().build()
    }
}