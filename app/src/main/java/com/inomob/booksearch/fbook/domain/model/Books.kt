package com.inomob.booksearch.fbook.domain.model
import com.inomob.booksearch.fbook.data.remote.dto.Doc

data class Books(
    val numFound: Int,
    val docs: List<Doc>
)
