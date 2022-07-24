package com.inomob.booksearch.fbook.domain.model

data class BookDetail(
    val description: String? = "",
    val key: String? = "",
    val location: String? = "",
    val subject_people: List<String>? = emptyList(),
    val subject_places: List<String>? = emptyList(),
    val subject_times: List<String>? = emptyList(),
    val subjects: List<String>? = emptyList(),
    val title: String? = "",
)
