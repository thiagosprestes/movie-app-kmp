package com.example.movie.presentation.composables.defaultState

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
fun movieScreenSimilar(
    title: String,
    similarMovies: List<HomeMovie>,
    onNavigateToMovie: (movieId: Long) -> Unit
) {
    movieScreenList(
        title = title,
        list = similarMovies,
    ) {
        Box(
            Modifier
                .width(150.dp)
                .height(243.dp)
                .padding(end = 10.dp)
                .clip(RoundedCornerShape(10.dp)).clickable {
                    onNavigateToMovie(it.id)
                }
        ) {
            AsyncImage(
                model = it.posterPath,
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
                    it.title,
                    color = primaryWhite,
                    fontSize = 14.sp
                )
            }
        }
    }
}