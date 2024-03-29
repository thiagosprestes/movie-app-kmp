package ui.screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import data.model.Movie.Movie
import ui.components.Error
import ui.components.Loading
import ui.screens.Home.components.Carousel
import ui.screens.Home.components.MoviesList
import ui.theme.backgroundGradient

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<HomeScreenModel>()
        val state by screenModel.state.collectAsState()

        Column(
            Modifier.background(
                backgroundGradient
            ).fillMaxSize()
        ) {
            when (state) {
                is HomeScreenModel.State.Loading -> Loading()
                is HomeScreenModel.State.Error -> Error(onRetry = { screenModel.handleOnRetry() })
                is HomeScreenModel.State.Default -> Default(
                    nowPlaying = screenModel.nowPlaying.value,
                    trending = screenModel.trending.value,
                    upcoming = screenModel.upcoming.value
                )
            }
        }
    }
}

@Composable
fun Default(
    nowPlaying: List<Movie>,
    trending: List<Movie>,
    upcoming: List<Movie>
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 28.dp)
    ) {
        Carousel(nowPlaying)
        MoviesList(title = "Em alta", movies = trending)
        MoviesList(title = "Novos lançamentos", movies = upcoming)
    }
}
