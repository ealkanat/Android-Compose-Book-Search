package com.inomob.booksearch.fbook.domain.use_case

import com.inomob.booksearch.fbook.common.Constants
import com.inomob.booksearch.fbook.domain.model.Books
import javax.inject.Inject

// this useCase class Generates the Cover Image URLs that BookView needs
class MakeCoverUrlUseCase @Inject constructor() {

    operator fun invoke(books: Books?): Books? {
        if (books != null) {
            return books.copy(
                books = books.books.map {
                    it.copy( cover_url = Constants.COVER_URL + it.cover_i.toString() + "-M.jpg" )
                }
            )
        }
        return null
    }

}