package com.example.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.presentation.composables.UiStateContent
import com.example.core.presentation.theme.backgroundGradient
import com.example.core.utils.windowInsetsPadding
import com.example.home.presentation.composables.homeCarousel
import com.example.home.presentation.composables.homeMoviesList
import com.example.home.presentation.model.OnInitHomeScreen
import com.example.navigation.SharedScreen
import com.example.navigation.utils.getScreenRegistry

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()
        val uiState by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(screenModel) {
            screenModel.handleAction(OnInitHomeScreen)
        }

        fun onGoToMovie(movieId: Long) =
            navigator.push(getScreenRegistry(SharedScreen.Movie(movieId)))

        UiStateContent(uiState, onRetry = {
            screenModel.handleAction(OnInitHomeScreen)
        }).render {
            Column(
                Modifier
                    .background(backgroundGradient)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 28.dp).windowInsetsPadding(
                        WindowInsets.safeDrawing
                            .only(WindowInsetsSides.Top)
                            .asPaddingValues()
                            .calculateTopPadding()
                    ),
            ) {
                homeCarousel(movies = it.nowPlayingMovies) {
                    onGoToMovie(it)
                }
                homeMoviesList(sectionUiModel = it.trendingMovies) {
                    onGoToMovie(it)
                }
                homeMoviesList(sectionUiModel = it.upcomingMovies) {
                    onGoToMovie(it)
                }
            }
        }
    }
}