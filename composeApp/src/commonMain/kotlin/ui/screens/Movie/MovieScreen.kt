package ui.screens.Movie

import DateTime
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import data.model.MovieDetail.MovieDetailResponse
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ui.components.RatingLevel
import ui.theme.background
import ui.theme.primaryWhite

data class MovieScreen(val id: Int) : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<MovieScreenModel>()
        val state by screenModel.state.collectAsState()

        when (state) {
            is MovieScreenModel.State.Loading -> Loading()
            is MovieScreenModel.State.Error -> Default(null)
            is MovieScreenModel.State.Default -> Default(movie = screenModel.details.value)
        }

        LaunchedEffect(key1 = screenModel) {
            screenModel.getNowPlaying(id)
        }
    }
}

@Composable
fun Default(movie: MovieDetailResponse?) {
    Column(
        Modifier
            .background(background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            KamelImage(
                resource = asyncPainterResource("https://image.tmdb.org/t/p/original/${movie?.backdropPath}"),
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
                    movie?.title!!,
                    color = primaryWhite,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Row {
                    LazyRow {
                        itemsIndexed(movie.genres.take(2), key = { _, it -> it.id }) { index, it ->
                            Text(
                                "${it.name}${if (index < movie.genres.lastIndex) ", " else " "}",
                                color = primaryWhite,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(vertical = 16.dp)
                            )
                        }
                    }
                    Text(
                        "| ${movie.runtime} minutos | ${DateTime.getFormattedDate(movie.releaseDate)}",
                        color = primaryWhite,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RatingLevel(movie.voteAverage, 16.dp)
                    Text(
                        "(${movie.voteCount})",
                        color = primaryWhite,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }
        Column(
            Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                "Descrição",
                color = primaryWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 12.dp).padding(horizontal = 16.dp)
            )
            Text(
                movie?.overview!!,
                color = primaryWhite.copy(alpha = 0.8F),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(bottom = 30.dp).padding(horizontal = 16.dp)
            )
            Text(
                "Elenco",
                color = primaryWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
            )
            LazyRow(contentPadding = PaddingValues(start = 16.dp, bottom = 30.dp)) {
                items(5, key = { it }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(end = 23.dp)
                    ) {
                        KamelImage(
                            asyncPainterResource("https://image.tmdb.org/t/p/original/BE2sdjpgsa2rNTFa66f7upkaOP.jpg"),
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .size(90.dp)
                                .clip(RoundedCornerShape(50.dp)),
                            contentDescription = null,
                        )
                        Text(
                            "Ator",
                            color = primaryWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            "Personagem",
                            color = primaryWhite,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light,
                        )
                    }
                }
            }
            Text(
                "Filmes semelhantes",
                color = primaryWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
            )
            LazyRow(contentPadding = PaddingValues(start = 16.dp, bottom = 30.dp)) {
                items(5, key = { it }) {
                    Box(
                        Modifier
                            .width(150.dp)
                            .height(243.dp)
                            .padding(end = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        KamelImage(
                            resource = asyncPainterResource("https://image.tmdb.org/t/p/original/BE2sdjpgsa2rNTFa66f7upkaOP.jpg"),
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
                                "Teste",
                                color = primaryWhite,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Loading() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = primaryWhite)
    }
}