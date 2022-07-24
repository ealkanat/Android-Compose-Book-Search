package com.inomob.booksearch.fbook.presentation.search_book

// Book details state keeps state of detail sections
data class DetailState(
    val isDetailOpened: Boolean = false,
    val expandLanguageView: Boolean = false,
    val expandTimeView: Boolean = false,
    val expandPlacesView: Boolean = false,
    val expandPeopleView: Boolean = false,
    val expandFirstSentenceView: Boolean = false,
)
