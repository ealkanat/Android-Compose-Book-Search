package com.inomob.booksearch.fbook.domain.use_case

import com.inomob.booksearch.fbook.domain.util.ExpandType
import com.inomob.booksearch.fbook.presentation.search_book.BooksState
import com.inomob.booksearch.fbook.presentation.search_book.DetailState
import javax.inject.Inject

// UseCase for open / close the Card view details
// It close all other cards and opens current card detail section
class ExpandCloseDetailsUseCase @Inject constructor() {

    operator fun invoke(
        key: String,
        state: BooksState,
        expandType: ExpandType = ExpandType.CardViewDetail
    ): BooksState {

        val nDetailStates: MutableMap<String, DetailState> = state.detailStates.toMutableMap()
        nDetailStates.forEach{ entry ->
            val copy: DetailState = nDetailStates[entry.key]!!.copy(isDetailOpened = false)
            nDetailStates[entry.key] = copy
        }

        when(expandType){
            is ExpandType.CardViewDetail -> {
                if(state.detailStates[key] == null){
                    nDetailStates[key] = DetailState(isDetailOpened = true)
                }else{
                    nDetailStates[key] = state.detailStates[key]!!.copy(
                        isDetailOpened = state.detailStates[key]!!.isDetailOpened == false
                    )
                }
            }
            is ExpandType.LanguageView -> {
                nDetailStates[key] = state.detailStates[key]!!.copy(
                    expandLanguageView = state.detailStates[key]!!.expandLanguageView == false
                )
            }
            is ExpandType.PeopleView -> {
                nDetailStates[key] = state.detailStates[key]!!.copy(
                    expandPeopleView = state.detailStates[key]!!.expandPeopleView == false
                )
            }
            is ExpandType.PlacesView -> {
                nDetailStates[key] = state.detailStates[key]!!.copy(
                    expandPlacesView = state.detailStates[key]!!.expandPlacesView == false
                )
            }
            is ExpandType.TimeView -> {
                nDetailStates[key] = state.detailStates[key]!!.copy(
                    expandTimeView = state.detailStates[key]!!.expandTimeView == false
                )
            }
            is ExpandType.FirstSentenceView -> {
                nDetailStates[key] = state.detailStates[key]!!.copy(
                    expandFirstSentenceView = state.detailStates[key]!!.expandFirstSentenceView == false
                )
            }
        }


        return state.copy(
            detailStates = nDetailStates.toMap()
        )
    }

}