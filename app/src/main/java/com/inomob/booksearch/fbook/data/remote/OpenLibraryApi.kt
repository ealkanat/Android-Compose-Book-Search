package com.inomob.booksearch.fbook.data.remote

import com.inomob.booksearch.fbook.data.remote.dto.BooksDto
import com.inomob.booksearch.fbook.data.remote.dto.WorksDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET("/search.json?limit=10")
    suspend fun searchBook(@Query("q") search: String): BooksDto
}