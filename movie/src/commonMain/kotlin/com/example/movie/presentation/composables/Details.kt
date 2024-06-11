package com.example.movie.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.core.data.model.movieDetail.Genre
import com.example.core.presentation.composables.RatingLevel
import com.example.core.presentation.theme.backgroundEnd
import com.example.core.presentation.theme.primaryWhite
import com.example.core.utils.DateTime

@Composable
fun Details(
    backdrop: String? = null,
    title: String,
    genres: List<Genre>,
    runtime: Int,
    releaseDate: String,
    voteAverage: Double,
    voteCount: Int
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        AsyncImage(
            model = backdrop,
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
                LazyRow {
                    itemsIndexed(genres.take(2), key = { _, item -> item.id }) { index, it ->
                        Text(
                            "${it.name}${if (index < genres.lastIndex) ", " else " "}",
                            color = primaryWhite,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }
                }
                Text(
                    "| $runtime minutos | ${DateTime.getFormattedDate(releaseDate)}",
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