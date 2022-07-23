package com.inomob.booksearch.fbook.data.remote.dto

import com.inomob.booksearch.fbook.domain.model.Books

data class BooksDto(
    val docs: List<Doc>,
    val numFound: Int,
    val numFoundExact: Boolean,
    val num_found: Int,
    val offset: Any,
    val q: String,
    val start: Int
)

fun BooksDto.toBooks(): Books {
    return Books(
        numFound = numFound,
        books = docs.map { it.toBook() }
    )
}