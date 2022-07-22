package com.inomob.booksearch.fbook.data.remote

import com.inomob.booksearch.fbook.data.remote.dto.BooksDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET("/search.json")
    suspend fun searchBook(@Query("q") search: String): BooksDto
}