package com.example.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
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
private fun carouselIndicator(pagerState: PagerState) {
    LazyRow(
        modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(pagerState.pageCount, key = { it }) {
            val color = when {
                pagerState.currentPage == it -> primaryWhite
                else -> primaryWhite.copy(
                    alpha = 0.3f
                )
            }

            val size = if (pagerState.currentPage == it) 10.dp else 8.dp

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .background(color, CircleShape)
                    .size(size)
            )
        }
    }
}

@Composable
private fun carouselItem(
    movie: HomeMovie,
    onGoToMovie: (Long) -> Unit = {}
) {
    Box(
        Modifier
            .height(190.dp)
            .width(400.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onGoToMovie(movie.id)
            }
    ) {
        AsyncImage(
            model = movie.backdropPath,
            contentScale = ContentScale.FillBounds,
            contentDescription = null
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
            modifier = Modifier.align(Alignment.BottomStart).padding(22.dp)
        ) {
            Text(movie.title, color = primaryWhite, fontSize = 18.sp)
        }
    }
}

@Composable
fun homeCarousel(
    movies: List<HomeMovie>,
    onGoToMovie: (Long) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = {
        movies.count()
    })

    Column {
        HorizontalPager(
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = 15.dp,
            state = pagerState
        ) { currentPage ->
            carouselItem(movie = movies[currentPage], onGoToMovie = onGoToMovie)
        }
        carouselIndicator(pagerState)
    }
}