package com.inomob.booksearch.fbook.data.repository

import com.inomob.booksearch.fbook.data.remote.OpenLibraryApi
import com.inomob.booksearch.fbook.data.remote.dto.BooksDto
import com.inomob.booksearch.fbook.domain.repository.BookRepository
import javax.inject.Inject

// This is book repository implementation class,
// application use this implementation, application
// does not need to know where the data comes from
// we can use API call Room Database or the dummy data
class BookRepositoryImpl @Inject constructor(
    private val api : OpenLibraryApi
) : BookRepository {
    override suspend fun searchBook(search: String): BooksDto {
        return api.searchBook(search)
    }
}