package com.example.movie.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.data.model.ScreenState
import com.example.core.presentation.composables.Error
import com.example.core.presentation.composables.Loading
import com.example.core.presentation.theme.backgroundEnd
import com.example.movie.presentation.composables.defaultState.MovieScreenDefaultStateParams
import com.example.movie.presentation.composables.defaultState.movieScreenDefaultState
import com.example.movie.presentation.composables.movieScreenHeader
import com.example.navigation.SharedScreen
import com.example.navigation.utils.getScreenRegistry

data class MovieScreen(val id: Long) : Screen {
    override val key = uniqueScreenKey

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<MovieScreenModel>()
        val state by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = screenModel) {
            screenModel::handleAction.invoke(OnInitMovieScreen(id))
        }

        Column(Modifier.background(backgroundEnd).fillMaxSize()) {
            Column {
                Box {
                    when (state.state) {
                        ScreenState.LOADING -> Loading()
                        ScreenState.ERROR -> Error {
                            screenModel::handleAction.invoke(OnInitMovieScreen(id))
                        }

                        ScreenState.DEFAULT -> movieScreenDefaultState(
                            params = MovieScreenDefaultStateParams(
                                header = state.header,
                                details = state.details,
                                casting = state.casting,
                                similar = state.similar,
                            ) { movieId ->
                                navigator.push(getScreenRegistry(SharedScreen.Movie(movieId)))
                            }
                        )
                    }
                    movieScreenHeader(
                        isDefaultState = state.state == ScreenState.DEFAULT,
                        starColor = state.header.starColor,
                        onGoBack = { navigator.pop() },
                    ) {
                        screenModel::handleAction.invoke(
                            OnToggleFavoriteMovie
                        )
                    }
                }
            }
        }
    }
}
