package com.example.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.example.core.data.model.ScreenState
import com.example.core.presentation.composables.Error
import com.example.core.presentation.composables.Loading
import com.example.home.presentation.composables.Default

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
