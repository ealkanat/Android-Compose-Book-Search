package com.inomob.booksearch.fbook.domain.repository

import com.inomob.booksearch.fbook.data.remote.dto.BooksDto

// BookRepository Interface; I use this interface because
// we can create a fake repository for test or other purposes
interface BookRepository {
    suspend fun searchBook(search: String): BooksDto
}