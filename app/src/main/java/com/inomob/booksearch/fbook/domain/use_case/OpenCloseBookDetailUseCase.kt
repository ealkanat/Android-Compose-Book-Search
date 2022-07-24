package com.inomob.booksearch.fbook.domain.use_case

import com.inomob.booksearch.fbook.presentation.search_book.BooksState
import com.inomob.booksearch.fbook.presentation.search_book.DetailState
import javax.inject.Inject

// UseCase for open / close the Card view details
// It close all other cards and opens current card detail section
class OpenCloseBookDetailUseCase @Inject constructor() {

    operator fun invoke(key: String, state: BooksState): BooksState {
        val nDetailStates: MutableMap<String, DetailState> = state.detailStates.toMutableMap()
        nDetailStates.forEach{ entry ->
            val copy: DetailState = nDetailStates[entry.key]!!.copy(isDetailOpened = false)
            nDetailStates[entry.key] = copy
        }
        if(state.detailStates[key] == null){
            nDetailStates[key] = DetailState(isDetailOpened = true)
        }else{
            nDetailStates[key] = state.detailStates[key]!!.copy(
                isDetailOpened = state.detailStates[key]!!.isDetailOpened == false
            )
        }
        return state.copy(
            detailStates = nDetailStates.toMap()
        )
    }

}