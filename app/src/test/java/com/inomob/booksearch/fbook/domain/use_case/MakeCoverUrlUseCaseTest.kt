package com.inomob.booksearch.fbook.domain.use_case

import com.inomob.booksearch.fbook.common.Constants
import com.inomob.booksearch.fbook.domain.model.Book
import com.inomob.booksearch.fbook.domain.model.Books
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

// This usecase tests Make Cover URL UseCase
class MakeCoverUrlUseCaseTest {

    private lateinit var makeCoverUrlUseCase: MakeCoverUrlUseCase

    @Before
    fun setUp(){
        makeCoverUrlUseCase = MakeCoverUrlUseCase()
    }

    @Test
    fun `Test Make Cover URL` () {
        val books = Books(
            numFound = 0,
            books = listOf<Book>(Book(
                title = "",
                subtitle = "",
                cover_url = "",
                publish_year = emptyList(),
                author_name = emptyList(),
                type = "",
                cover_i = 1
            ))
        )
        val booksResult = makeCoverUrlUseCase(books)
        assertEquals(Constants.COVER_URL + "1" + "-M.jpg",
            booksResult!!.books[0].cover_url
        )
    }
}