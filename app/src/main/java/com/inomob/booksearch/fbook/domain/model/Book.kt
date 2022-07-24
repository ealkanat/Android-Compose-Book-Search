package com.inomob.booksearch.fbook.domain.model

data class Book(
    val key: String,
    val authors: List<Author>,
    val cover_i: Int?,
    val cover_url: String,
    val publish_year: List<Int>,
    val first_sentence: String,
    val subtitle: String,
    val title: String,
    val type: String,
    val language: List<String>,
    val person: List<String>,
    val time: List<String>,
    val place: List<String>,
)
