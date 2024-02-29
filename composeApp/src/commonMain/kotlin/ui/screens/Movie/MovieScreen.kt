package ui.screens.Movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import data.model.Movie.Movie
import data.model.MovieCredits.Cast
import data.model.MovieDetail.MovieDetailResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ui.components.Error
import ui.components.Loading
import ui.screens.Movie.components.Cast
import ui.screens.Movie.components.Details
import ui.screens.Movie.components.Similar
import ui.theme.background
import ui.theme.primaryWhite

data class MovieScreen(val id: Int) : Screen {
    override val key = "screenId_$id"

    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<MovieScreenModel>()
        val state by screenModel.state.collectAsState()

        fun handleOnGetData() {
            GlobalScope.launch {
                screenModel.getDetails(id)
                screenModel.getCredits(id)
                screenModel.getSimilar(id)
            }
        }

        Column(Modifier.background(background).fillMaxSize()) {
            when (state) {
                is MovieScreenModel.State.Loading -> Loading()
                is MovieScreenModel.State.Error -> Error(onRetry = { handleOnGetData() })
                is MovieScreenModel.State.Default -> {
                    Default(
                        movie = screenModel.details.value,
                        cast = screenModel.cast.value,
                        similar = screenModel.similar.value
                    )
                }
            }
        }

        LaunchedEffect(key1 = screenModel) {
            screenModel.getDetails(id)
            screenModel.getCredits(id)
            screenModel.getSimilar(id)
        }
    }
}

@Composable
fun Default(movie: MovieDetailResponse?, cast: List<Cast>, similar: List<Movie>) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Details(
            movie?.backdropPath!!,
            movie.title,
            movie.genres,
            movie.runtime,
            movie.releaseDate,
            movie.voteAverage,
            movie.voteCount
        )
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
                movie.overview,
                color = primaryWhite.copy(alpha = 0.8F),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(bottom = 30.dp).padding(horizontal = 16.dp)
            )
            Cast(cast)
            Similar(similar)
        }
    }
}

