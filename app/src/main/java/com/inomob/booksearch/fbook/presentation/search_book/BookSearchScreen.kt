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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.inomob.booksearch.fbook.presentation.search_book.components.BookView
import com.inomob.booksearch.fbook.presentation.search_book.components.BookViewSkeleton

@Composable
fun BookSearchScreen(
    viewModel: BooksViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val localFocusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.padding(top = 44.dp),
        topBar = {
                 Row (
                     modifier = Modifier
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
                                 viewModel.onEvent(BooksEvent.SearchBook(state.searchString))
                                 viewModel.onEvent(BooksEvent.SetSearchText(state.searchString))
                                 localFocusManager.clearFocus()
                             }
                         ),
                         onValueChange = {
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
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(2.dp),
                         colors = TextFieldDefaults.textFieldColors(
                             focusedIndicatorColor = Transparent,
                             unfocusedIndicatorColor = Transparent)
                     )
                 }
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()){
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 60.dp)
                ) {
                    state.books?.let {
                        items(it.docs.take(10)) { doc ->
                            BookView(doc = doc)
                        }
                    }
                }
            }

            if(state.error.isNotBlank()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = state.error,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                    )
                }
            }

            if(state.isLoading){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(modifier = Modifier.fillMaxSize()){
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
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
    )

}