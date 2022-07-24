package com.inomob.booksearch.fbook.presentation.search_book
import com.inomob.booksearch.fbook.domain.model.Books

data class BooksState(
    val isLoading : Boolean = false,
    val books : Books? = null,
    val error : String = "",
    val validationError : String = "",
    val searchString : String = "The Lord Of The Rings",
    val detailStates: Map<String, DetailState> = emptyMap(),
)
