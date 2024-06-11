package ui.screens.Movie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.data.model.movieCredits.Cast
import com.example.core.data.model.movieDetail.MovieDetailResponse
import data.model.Movie.Movie
import io.github.aakira.napier.Napier
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ui.components.Error
import ui.components.Loading
import ui.screens.Movie.components.Cast
import ui.screens.Movie.components.Details
import ui.screens.Movie.components.Similar
import ui.theme.backgroundEnd
import ui.theme.primaryWhite

data class MovieScreen(val id: Int) : Screen {
    override val key = "screenId_$id"
    private val LOG_TAG = "MovieScreen"

    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<MovieScreenModel>()
        val state by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        fun handleOnGetData() {
            GlobalScope.launch {
                screenModel.getDetails(id)
                screenModel.getCredits(id)
                screenModel.getSimilar(id)
            }
        }

        Column(Modifier.background(backgroundEnd).fillMaxSize()) {
            Column {
                Box {
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
                    Icon(
                        Icons.Filled.ArrowBack,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                            .clickable {
                                Napier.i(tag = LOG_TAG, message = "press back button")
                                navigator.pop()
                            },
                        tint = primaryWhite,
                        contentDescription = null,
                    )
                }
            }
        }

        LaunchedEffect(key1 = screenModel) {
            Napier.i(tag = LOG_TAG, message = "init screen")
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
            movie?.backdropPath,
            movie?.title!!,
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

