package com.inomob.booksearch.fbook.data.remote.dto

import com.inomob.booksearch.fbook.common.Constants
import com.inomob.booksearch.fbook.domain.model.Author
import com.inomob.booksearch.fbook.domain.model.Book

data class Doc(
    val key: String,
    val author_name: List<String>?,
    val author_key: List<String>,
    val cover_i: Int,
    val edition_count: Int,
    val first_publish_year: Int,
    val first_sentence: List<String>?,
    val has_fulltext: Boolean,
    val ia: List<String>,
    val public_scan_b: Boolean,
    val title: String?,
    val language: List<String>?,
    val person: List<String>?,
    val person_facet: List<String>,
    val person_key: List<String>,
    val place: List<String>?,
    val place_facet: List<String>,
    val place_key: List<String>,
    val publish_date: List<String>,
    val publish_place: List<String>,
    val publish_year: List<Int>?,
    val publisher: List<String>,
    val publisher_facet: List<String>,
    val seed: List<String>,
    val subject: List<String>,
    val subject_facet: List<String>,
    val subject_key: List<String>,
    val subtitle: String?,
    val time: List<String>?,
    val time_facet: List<String>,
    val time_key: List<String>,
    val title_suggest: String,
    val type: String?
)

fun Doc.toBook(): Book {

    val nAuthors = mutableListOf<Author>()
    if(!author_name.isNullOrEmpty()){
        for((index, value) in author_name.withIndex()){
            nAuthors.add(Author(
                name = value,
                imageUrl = Constants.AUTHOR_IMG_URL + author_key[index] + "-S.jpg"
            ))
        }
    }

    return Book(
        key = key,
        title = title?: "",
        subtitle = subtitle?: "",
        type = type?: "",
        authors = nAuthors,
        publish_year = publish_year?: emptyList(),
        cover_i = cover_i,
        first_sentence = first_sentence?.get(0) ?: "",
        language = language ?: emptyList(),
        person = person ?: emptyList(),
        time = time ?: emptyList(),
        place = place ?: emptyList(),
        cover_url = ""
    )
}