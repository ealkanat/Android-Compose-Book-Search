package com.inomob.booksearch.fbook.presentation.search_book

sealed class BooksEvent {
    data class SearchBook(val search: String) : BooksEvent()
    data class SetSearchText(val search: String) : BooksEvent()
    object ClearSearchText : BooksEvent()
}
