package com.inomob.booksearch.fbook.presentation

sealed class Route(val route: String) {
    object BookSearchScreen: Route("book_search_screen")
}