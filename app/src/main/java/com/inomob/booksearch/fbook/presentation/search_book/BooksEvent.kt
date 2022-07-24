package com.inomob.booksearch.fbook.presentation.search_book

import com.inomob.booksearch.fbook.domain.util.ExpandType

// All viewModel events are defined here
sealed class BooksEvent {
    data class SearchBook(val search: String) : BooksEvent()
    data class SetSearchText(val search: String) : BooksEvent()
    data class SetValidationError(val message: String) : BooksEvent()
    data class ExpandCloseDetails(
        val key: String,
        val expandType: ExpandType = ExpandType.CardViewDetail) : BooksEvent()
    object ClearSearchText : BooksEvent()
}
