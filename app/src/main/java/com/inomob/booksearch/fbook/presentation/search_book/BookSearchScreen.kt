package com.inomob.booksearch.fbook.presentation.search_book

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.inomob.booksearch.fbook.domain.model.Books
import com.inomob.booksearch.fbook.presentation.search_book.components.BookView
import com.inomob.booksearch.fbook.presentation.search_book.components.BookViewSkeleton

@Composable
fun BookSearchScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: BooksViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val localFocusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.padding(top = 44.dp),
        scaffoldState = scaffoldState,
        snackbarHost = {
            // Modifying default snackbar
            SnackbarHost(it) { data ->
                Snackbar(
                    backgroundColor = Yellow,
                    contentColor = Black,
                    snackbarData = data,
                    modifier = modifier.padding(bottom = 50.dp)
                )
            }
        },
        topBar = {
                 Row (
                     modifier = modifier
                         .height(70.dp)
                         .fillMaxWidth()
                         .padding(
                             horizontal = 12.dp,
                             vertical = 6.dp
                         ),
                     verticalAlignment = Alignment.CenterVertically
                 ){
                     TextField(
                         value = state.searchString,
                         shape = RoundedCornerShape(32.dp),
                         singleLine = true,
                         keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                         keyboardActions = KeyboardActions(
                             onSearch = {
                                 // if the search string is empty, validation error shows
                                 if(state.searchString.isBlank()){
                                     viewModel.onEvent(
                                         BooksEvent.SetValidationError(
                                             "Search can't be empty"))
                                 }else{
                                     viewModel.onEvent(BooksEvent.SearchBook(state.searchString))
                                     viewModel.onEvent(BooksEvent.SetSearchText(state.searchString))
                                 }
                                 localFocusManager.clearFocus()
                             }
                         ),
                         onValueChange = {
                             // users can't search the string that has more than 150 chars
                             if (it.length <= 150) {
                                 viewModel.onEvent(BooksEvent.SetSearchText(it))
                             }
                         },
                         leadingIcon = {
                             Icon(
                                 imageVector = Icons.Outlined.Search,
                                 contentDescription = null
                             )
                         },
                         trailingIcon = {
                             if (state.searchString.isNotEmpty()) {
                                 IconButton(
                                     onClick = {
                                         viewModel.onEvent(
                                             BooksEvent.ClearSearchText
                                         )
                                         localFocusManager.clearFocus()
                                     }) {
                                     Icon(
                                         imageVector = Icons.Outlined.Close,
                                         contentDescription = null
                                     )
                                 }
                             }
                         },
                         placeholder = {
                             Text(text = "Search books")
                         },
                         modifier = modifier
                             .fillMaxWidth()
                             .padding(2.dp),
                         colors = TextFieldDefaults.textFieldColors(
                             focusedIndicatorColor = Transparent,
                             unfocusedIndicatorColor = Transparent)
                     )
                 }
        },
    ){
        Box(modifier = modifier.fillMaxSize()){
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 60.dp)
            ) {
                state.books?.let {
                    items(it.books.take(10)) { book ->
                        BookView(book = book)
                    }
                }
            }
        }

        // Show snackbar for validation errors
        if(state.validationError.isNotBlank()){
            LaunchedEffect(scaffoldState.snackbarHostState){
                scaffoldState.snackbarHostState.showSnackbar(
                    state.validationError
                )
                // Notify the viewModel if message displayed
                viewModel.validationErrorShown()
            }
        }

        // Display error message when book search has error
        if(state.error.isNotBlank()){
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = state.error,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.error,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                )
            }
        }

        // Display loading state of UI
        if(state.isLoading){
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box(modifier = modifier.fillMaxSize()){
                    LazyColumn(
                        modifier = modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 60.dp)
                    ) {
                        items((1.. 10).toList()){
                            BookViewSkeleton()
                        }
                    }
                }
            }
        }
    }

}