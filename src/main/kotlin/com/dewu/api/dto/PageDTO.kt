package com.dewu.api.dto

import lombok.Data
import org.springframework.data.domain.Page

@Data
data class PageDTO<T>(
        val data: List<T>,
        val limit: Int,
        val page: Int,
        val totalPages: Int,
        val totalElements: Long,
) {
    constructor(page: Page<T>) : this(
            data = page.content,
            totalPages = page.totalPages,
            totalElements = page.totalElements,
            limit = page.size,
            page = page.number
    )
}
