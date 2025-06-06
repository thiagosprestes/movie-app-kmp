package com.example.movie.presentation.composables.defaultState

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.core.presentation.composables.FontSize
import com.example.core.presentation.composables.Icon
import com.example.core.presentation.composables.Text
import com.example.core.presentation.theme.backgroundEnd
import com.example.core.presentation.theme.primaryWhite
import com.example.core.presentation.theme.secondaryGrey
import com.example.core.presentation.theme.transparent
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Clock
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun movieScreenBackdrop(
    title: String,
    releaseDate: String,
    runtime: String,
    percent: Float,
    backdropPath: String,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(400.dp)
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
                        listOf(transparent, backgroundEnd),
                        0f,
                        850f
                    )
                )
        )
        Row(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 10.dp),
        ) {
            movieScreenRateProgressCircle(
                percent = percent,
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = title,
                    color = primaryWhite,
                    fontSize = FontSize.LARGE_X,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    Text(
                        text = releaseDate,
                        color = secondaryGrey,
                        fontSize = FontSize.LARGE,
                    )
                    Text(
                        text = "•",
                        color = secondaryGrey,
                        fontSize = FontSize.LARGE,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            icon = FontAwesomeIcons.Regular.Clock,
                            modifier = Modifier.padding(end = 6.dp),
                            color = secondaryGrey,
                            size = 20,
                        )
                        Text(
                            text = runtime,
                            color = secondaryGrey,
                            fontSize = FontSize.LARGE,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieScreenHeaderPreview() {
    movieScreenBackdrop(
        title = "Batman: The Dark Knight",
        releaseDate = "2008",
        runtime = "2h 32m",
        percent = 0.75f,
        backdropPath = "https://image.tmdb.org/t/p/original/8j2d1k2d1k2d1k2d1k2d1k2d1k2d1k2.jpg"
    )
}