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
import com.example.home.presentation.composables.homeScreenDefaultState
import com.example.home.presentation.model.OnInitHomeScreen
import com.example.home.presentation.model.OnSelectOption
import com.example.navigation.SharedScreen
import com.example.navigation.utils.getScreenRegistry

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()
        val uiState by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(screenModel) {
            screenModel::handleAction.invoke(OnInitHomeScreen)
        }

        fun onGoToMovie(movieId: Long) =
            navigator.push(getScreenRegistry(SharedScreen.Movie(movieId)))

        when (uiState.state) {
            ScreenState.DEFAULT -> homeScreenDefaultState(
                nowPlayingMovies = uiState.moviesUiModel.nowPlayingMovies,
                trendingMovies = uiState.moviesUiModel.trendingMovies,
                upcomingMovies = uiState.moviesUiModel.upcomingMovies,
                options = uiState.types,
                selectedOption = uiState.selectedType,
                onGoToMovie = { onGoToMovie(it) },
                onSelectOption = { screenModel::handleAction.invoke(OnSelectOption(it)) }
            )

            ScreenState.LOADING -> Loading()
            ScreenState.ERROR -> Error { screenModel::handleAction.invoke(OnInitHomeScreen) }
        }
    }
}