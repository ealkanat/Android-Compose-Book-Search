package com.inomob.booksearch.fbook.data.remote.dto
import com.inomob.booksearch.fbook.domain.model.BookDetail

data class WorksDto(
    val covers: List<Int>,
    val created: Created,
    val description: Description?,
    val key: String,
    val last_modified: LastModified,
    val latest_revision: Int,
    val location: String,
    val revision: Int,
    val subject_people: List<String>,
    val subject_places: List<String>,
    val subject_times: List<String>,
    val subjects: List<String>,
    val title: String,
    val type: TypeX
)

fun WorksDto.toBookDetail(): BookDetail {
    return BookDetail(
        description = description?.value ?: "",
        location = location,
        subject_people = subject_people,
        subject_places = subject_places,
        subject_times = subject_times,
        key = key,
    )
}