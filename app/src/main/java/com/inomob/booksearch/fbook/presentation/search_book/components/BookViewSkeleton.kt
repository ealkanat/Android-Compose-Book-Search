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
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.inomob.booksearch.fbook.common.Constants
import com.inomob.booksearch.fbook.data.remote.dto.Doc

@Composable
fun BookViewSkeleton(
    modifier: Modifier = Modifier
) {

    Card(
        shape = RoundedCornerShape(size = 6.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 8.dp,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 140.dp)
            .padding(8.dp)
            .height(140.dp),
    ) {
        Row (
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .width(90.dp)
            ) {
                Spacer(modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(Color.Gray),
                        color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)
                    )
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(6.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Spacer(modifier = modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .padding(6.dp)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(Color.Gray),
                        color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)
                    )
                )
                Spacer(modifier = modifier
                    .width(140.dp)
                    .height(28.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(Color.Gray),
                        color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)
                    )
                )
                Spacer(modifier = modifier
                    .width(60.dp)
                    .height(28.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(Color.Gray),
                        color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)
                    )
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