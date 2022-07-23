package com.inomob.booksearch.fbook.domain.model

data class Book(
    val authors: List<Author>,
    val cover_i: Int?,
    val cover_url: String,
    val publish_year: List<Int>,
    val subtitle: String,
    val title: String,
    val type: String
)
