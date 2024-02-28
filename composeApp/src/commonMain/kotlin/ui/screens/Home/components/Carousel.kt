package ui.screens.Home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import data.model.Movie.Movie
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.theme.primaryWhite

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(
    movies: List<Movie>
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
            CarouselItem(movies[currentPage])
        }
        CarouselIndicator(pagerState)
    }
}

@Composable
fun CarouselItem(movie: Movie) {
    Box(
        Modifier
            .height(190.dp)
            .width(320.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        KamelImage(
            resource = asyncPainterResource("https://image.tmdb.org/t/p/original/${movie.backdropPath}"),
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
            Text(movie.title!!, color = primaryWhite, fontSize = 18.sp)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselIndicator(pagerState: PagerState) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) primaryWhite else primaryWhite.copy(
                    alpha = 0.3f
                )
            val size = if (pagerState.currentPage == iteration) 10.dp else 8.dp

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .background(color, CircleShape)
                    .size(size)
            )
        }
    }
}