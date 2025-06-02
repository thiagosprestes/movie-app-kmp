package com.example.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.data.model.ScreenState
import com.example.core.presentation.composables.Error
import com.example.core.presentation.composables.Loading
import com.example.home.presentation.composables.homeDefault
import com.example.home.presentation.model.OnInitHomeScreen
import com.example.navigation.SharedScreen
import com.example.navigation.utils.getScreenRegistry

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()
        val state by screenModel.state.collectAsState()
        val screenState = state.state

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(screenModel) {
            screenModel::handleAction.invoke(OnInitHomeScreen)
        }

        when (screenState) {
            ScreenState.LOADING -> Loading()
            ScreenState.ERROR -> Error(onRetry = { screenModel::handleAction.invoke(OnInitHomeScreen) })
            ScreenState.DEFAULT -> homeDefault(
                nowPlaying = state.nowPlayingMovies,
                trending = state.trendingMovies,
                upcoming = state.upcomingMovies,
            ) { movieId ->
                navigator.push(getScreenRegistry(SharedScreen.Movie(movieId)))
            }
        }
    }
}
