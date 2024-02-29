package ui.screens.Movie.components

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
import data.model.MovieDetail.Genre
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.components.RatingLevel
import ui.theme.background
import ui.theme.primaryWhite
import utils.PATH_BASE_URL

@Composable
fun Details(
    backdrop: String,
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
        KamelImage(
            resource = asyncPainterResource("${PATH_BASE_URL}${backdrop}"),
            contentScale = ContentScale.FillHeight,
            contentDescription = null
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color.Transparent, background),
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
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Row {
                LazyRow {
                    itemsIndexed(genres.take(2), key = { _, it -> it.id }) { index, it ->
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