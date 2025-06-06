package com.example.search.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.core.data.model.HomeMovie
import com.example.core.presentation.composables.FontSize
import com.example.core.presentation.composables.Text
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
                fontSize = FontSize.SMALL
            )
        }
    }
}