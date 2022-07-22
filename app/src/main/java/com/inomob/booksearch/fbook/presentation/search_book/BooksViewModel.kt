package com.inomob.booksearch.fbook.presentation.search_book

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inomob.booksearch.fbook.common.Resource
import com.inomob.booksearch.fbook.domain.use_case.BookUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksUseCases : BookUseCases
) : ViewModel() {

    private val _state = mutableStateOf<BooksState>(BooksState())
    val state: State<BooksState> = _state
    private var searchJob: Job? = null
    private var lastSearch: String = "";

    // Search action performing initially
    init {
        searchBook("Lord Of The Rings")
    }

    fun onEvent(event: BooksEvent) {
        when (event){
            is BooksEvent.SearchBook -> {
                viewModelScope.launch {
                    if(event.search.isNotBlank()){
                        searchBook(event.search)
                    }
                }
            }
            is BooksEvent.ClearSearchText -> {
                _state.value = _state.value.copy(
                    searchString = ""
                )
            }
            is BooksEvent.SetSearchText -> {
                _state.value = _state.value.copy(
                    searchString = event.search
                )
            }
        }
    }

    private fun searchBook(search: String){
        // Stop recurring calls for search
        if (lastSearch == search) return
        // Cancel the old Job if it is not completed
        searchJob?.cancel()
        // Create search Job
        searchJob = booksUseCases.searchBook(search).onEach {
            result ->
            when (result){
                // If the API call successfully completed
                is Resource.Success -> {
                    lastSearch = search
                    _state.value = _state.value.copy(
                        error = "",
                        isLoading = false,
                        books = result.data
                    )
                }
                // If the api call in progress
                is Resource.Loading -> {
                    _state.value =_state.value.copy(
                        error = "",
                        isLoading = true,
                        books = null
                    )
                }
                // If the api call not executed with an error
                is Resource.Error -> {
                    _state.value =_state.value.copy(
                        error = result.message ?: "An error happened",
                        isLoading = false,
                        books = null
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}