package com.example.search.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.core.data.model.HomeMovie
import com.example.core.presentation.theme.primaryWhite

@Composable
fun searchResultListItem(
    movie: HomeMovie,
    onGoToMovie: (Long) -> Unit
) {
    Box(
        Modifier
            .width(120.dp)
            .height(213.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onGoToMovie(movie.id)
            }
    ) {
        AsyncImage(
            model = movie.posterPath,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        0F to Color.Transparent,
                        .5F to Color.Black.copy(alpha = 0.5F),
                        1F to Color.Black.copy(alpha = 0.8F)
                    )
                )
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        ) {
            Text(
                movie.title,
                color = primaryWhite,
                fontSize = 12.sp
            )
        }
    }
}