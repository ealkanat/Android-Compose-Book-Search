package com.inomob.booksearch.fbook.domain.use_case

import com.inomob.booksearch.fbook.common.Resource
import com.inomob.booksearch.fbook.data.remote.dto.toBooks
import com.inomob.booksearch.fbook.domain.model.Books
import com.inomob.booksearch.fbook.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


// This Use Case class for the business logic, with use case classes we can do
// business operations like order, sort, format date etc...
// In this case I use Use Case class just for return data without business logic.
class SearchBookUseCase @Inject constructor(
    private val repository: BookRepository
) {
     operator fun invoke(search: String) : Flow<Resource<Books>> = flow {
        try {
            emit(Resource.Loading())
            val books = repository.searchBook(search).toBooks()
            emit(Resource.Success(books))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An http exception occurred" ))
        } catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "An io exception occurred" ))
        }
    }
}