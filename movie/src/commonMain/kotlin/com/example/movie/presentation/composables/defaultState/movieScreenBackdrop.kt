package com.example.movie.presentation.composables.defaultState

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
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
import com.example.core.presentation.theme.backgroundEnd
import com.example.core.presentation.theme.primaryWhite
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
                        listOf(Color.Transparent, backgroundEnd),
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
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    Text(
                        text = releaseDate,
                        color = Color(0xFFBBBBBB),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "•",
                        color = Color(0xFFBBBBBB),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            FontAwesomeIcons.Regular.Clock,
                            modifier = Modifier.size(20.dp).padding(end = 6.dp),
                            tint = Color(0xFFBBBBBB),
                            contentDescription = null,
                        )
                        Text(
                            text = runtime,
                            color = Color(0xFFBBBBBB),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
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