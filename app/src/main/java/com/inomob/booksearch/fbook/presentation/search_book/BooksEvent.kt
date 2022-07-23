package com.inomob.booksearch.fbook.presentation.search_book

// All viewModel events are defined here
sealed class BooksEvent {
    data class SearchBook(val search: String) : BooksEvent()
    data class SetSearchText(val search: String) : BooksEvent()
    data class SetValidationError(val message: String) : BooksEvent()
    object ClearSearchText : BooksEvent()
}
