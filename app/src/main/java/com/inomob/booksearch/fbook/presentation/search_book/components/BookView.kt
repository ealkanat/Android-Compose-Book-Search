package com.inomob.booksearch.fbook.presentation.search_book.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.flowlayout.FlowRow
import com.inomob.booksearch.fbook.domain.model.Book

@Composable
fun BookView(
    book: Book,
    modifier: Modifier = Modifier
) {

    Card(
        shape = RoundedCornerShape(size = 6.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 140.dp)
            .padding(8.dp),
    ) {
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
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.padding(4.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
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
    }

}