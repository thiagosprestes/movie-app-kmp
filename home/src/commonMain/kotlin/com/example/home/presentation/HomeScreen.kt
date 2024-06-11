package com.example.home.presentation

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
import cafe.adriel.voyager.koin.koinScreenModel
import com.example.core.data.model.ScreenState
import com.example.core.presentation.composables.Error
import com.example.core.presentation.composables.Loading
import com.example.core.presentation.theme.backgroundGradient
import com.example.home.data.model.HomeMovie
import com.example.home.presentation.composables.Carousel
import com.example.home.presentation.composables.MoviesList

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()
        val state by screenModel.state.collectAsState()
        val screenState = state.state

        when (screenState) {
            ScreenState.LOADING -> Loading()
            ScreenState.ERROR -> Error(onRetry = { screenModel.handleOnRetry() })
            ScreenState.DEFAULT -> Default(
                nowPlaying = state.nowPlayingMovies,
                trending = state.trendingMovies,
                upcoming = state.upcomingMovies
            )
        }
    }
}

@Composable
fun Default(
    nowPlaying: List<HomeMovie>,
    trending: List<HomeMovie>,
    upcoming: List<HomeMovie>
) {
    Column(
        Modifier
            .background(backgroundGradient)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 28.dp, bottom = 50.dp)
    ) {
        Carousel(nowPlaying)
        MoviesList(title = "Em alta", movies = trending)
        MoviesList(title = "Novos lançamentos", movies = upcoming)
    }
}
