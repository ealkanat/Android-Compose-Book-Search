package com.inomob.booksearch.fbook.presentation.search_book.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.inomob.booksearch.fbook.domain.model.Book
import com.inomob.booksearch.fbook.presentation.search_book.BooksEvent
import com.inomob.booksearch.fbook.presentation.search_book.BooksViewModel

@Composable
fun BookView(
    book: Book,
    modifier: Modifier = Modifier,
    viewModel: BooksViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Card(
        shape = RoundedCornerShape(size = 6.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 140.dp)
            .padding(8.dp)
            .clickable {
                viewModel.onEvent(BooksEvent.OpenCloseBookDetail(book.key))
            },
    ) {
        Column() {
            Row (modifier = modifier.fillMaxSize()) {
                Column(
                    modifier = modifier
                        .fillMaxHeight()
                        .width(90.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(book.cover_url)
                            .size(Size.ORIGINAL)
                            .crossfade(true)
                            .build(),
                        contentDescription = book.title,
                        contentScale = ContentScale.Crop,
                        modifier = modifier.fillMaxSize()
                    )
                }
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(6.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = book.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier.padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    LazyRow(
                        modifier = modifier.fillMaxWidth()
                    ){
                        book.authors.forEach { author ->
                            item {
                                AuthorTagView(
                                    imageUrl = author.imageUrl,
                                    name = author.name)
                            }
                        }
                    }
                    Text(
                        text = if (book.publish_year.isNotEmpty()) book.publish_year[0].toString() else "",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
            //Book detail section
            if(state.detailStates.isNotEmpty()){
                if(state.detailStates[book.key]?.isDetailOpened == true){

                    //If book languages available show the languages.
                    if(book.language.isNotEmpty()) {
                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Language: ")
                                }
                                append(book.language.joinToString(", "))
                            },
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis,
                            modifier = modifier.padding(6.dp),
                            fontSize = 16.sp
                        )
                    }

                    //If book time available show the time.
                    if(book.time.isNotEmpty()) {
                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Time: ")
                                }
                                append(book.time.joinToString(", "))
                            },
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis,
                            modifier = modifier.padding(6.dp),
                            fontSize = 16.sp
                        )
                    }

                    //If book places available show the places.
                    if(book.place.isNotEmpty()) {
                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Places: ")
                                }
                                append(book.place.joinToString(", "))
                            },
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis,
                            modifier = modifier.padding(6.dp),
                            fontSize = 16.sp
                        )
                    }

                    //If book persons available show the persons.
                    if(book.person.isNotEmpty()) {
                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("People: ")
                                }
                                append(book.person.joinToString(", "))
                            },
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis,
                            modifier = modifier.padding(6.dp),
                            fontSize = 16.sp
                        )
                    }

                    //If book first sentence available show the first sentence.
                    if(book.first_sentence.isNotBlank()) {
                        Text(
                            text = "First Sentence",
                            overflow = TextOverflow.Ellipsis,
                            modifier = modifier.padding(6.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = book.first_sentence,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 5,
                            modifier = modifier.padding(6.dp),
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }

}