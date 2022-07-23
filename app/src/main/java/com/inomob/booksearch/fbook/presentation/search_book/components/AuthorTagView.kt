package com.inomob.booksearch.fbook.presentation.search_book.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun AuthorTagView(
    imageUrl: String,
    name: String,
    modifier: Modifier = Modifier
){

    Box(
        modifier = modifier
            .padding(4.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(100.dp)
            ).padding(horizontal = 8.dp, vertical = 4.dp)
    ){
        Row(){
            if(imageUrl.isNotBlank()){
                //Box using for creating layered view
                Box(){
                    // Circle background added for the images
                    Column(
                        modifier = modifier.fillMaxWidth()
                            .wrapContentSize(Alignment.Center)) {
                        Box(
                            modifier = modifier.size(20.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray)
                        )
                    }
                    // Top layer is image
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = name,
                        contentScale = ContentScale.Crop,
                        modifier = modifier.size(20.dp).clip(CircleShape),
                    )
                }
            }
            Text(
                text = name,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.padding(start = 4.dp)
            )
        }
    }

}