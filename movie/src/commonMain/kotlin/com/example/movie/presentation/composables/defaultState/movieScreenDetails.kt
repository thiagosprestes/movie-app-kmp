package com.example.movie.presentation.composables.defaultState

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.core.presentation.composables.RatingLevel
import com.example.core.presentation.theme.backgroundEnd
import com.example.core.presentation.theme.primaryWhite

data class MovieScreenDetailsParams(
    val backdropPath: String,
    val title: String,
    val genres: String,
    val runtime: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int
)

@Composable
fun movieScreenDetails(
    params: MovieScreenDetailsParams
) {
    with(params) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(450.dp)
        ) {
            AsyncImage(
                model = backdropPath,
                contentScale = ContentScale.FillHeight,
                contentDescription = null
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(Color.Transparent, backgroundEnd),
                            0f,
                            850f
                        )
                    )
            )
            Column(
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    title,
                    color = primaryWhite,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp
                )
                Row {
                    Text(
                        genres,
                        color = primaryWhite,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Text(
                        " $runtime$releaseDate",
                        color = primaryWhite,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RatingLevel(voteAverage, 16.dp)
                    Text(
                        "($voteCount)",
                        color = primaryWhite,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }
    }
}