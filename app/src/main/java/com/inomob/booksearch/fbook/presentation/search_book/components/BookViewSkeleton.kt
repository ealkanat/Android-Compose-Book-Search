package com.inomob.booksearch.fbook.presentation.search_book.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.inomob.booksearch.fbook.common.Constants
import com.inomob.booksearch.fbook.data.remote.dto.Doc

@Composable
fun BookViewSkeleton() {

    val brush = linearGradient(
        listOf(
            Color.LightGray.copy(alpha = 0.9f),
            Color.LightGray.copy(alpha = 0.4f),
            Color.LightGray.copy(alpha = 0.9f)
        )
    )

    Card(
        shape = RoundedCornerShape(size = 6.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 140.dp)
            .padding(8.dp),
    ) {
        Row (modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(90.dp)
            ) {
                Spacer(modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(10.dp))
                    .background(brush)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp)
                    .padding(6.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(brush)
                )
                Spacer(modifier = Modifier
                    .width(140.dp)
                    .height(28.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(brush)
                )
                Spacer(modifier = Modifier
                    .width(60.dp)
                    .height(28.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(brush)
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun BookViewSkeletonPreview() {
    BookViewSkeleton()
}