package com.example.movie.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.data.model.ScreenState
import com.example.core.presentation.composables.Error
import com.example.core.presentation.composables.Loading
import com.example.core.presentation.theme.backgroundEnd
import com.example.core.presentation.theme.primaryWhite
import com.example.core.utils.windowInsetsPadding
import com.example.movie.presentation.composables.Default
import com.example.navigation.SharedScreen
import com.example.navigation.utils.getScreenRegistry
import kotlinx.coroutines.DelicateCoroutinesApi

data class MovieScreen(val id: Long) : Screen {
    override val key = uniqueScreenKey

    @OptIn(DelicateCoroutinesApi::class)
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
                        ScreenState.ERROR -> Error(onRetry = {
                            screenModel::handleAction.invoke(OnInitMovieScreen(id))
                        })

                        ScreenState.DEFAULT -> state.details?.let {
                            Default(
                                movie = it,
                                cast = state.cast,
                                similar = state.similar,
                                onGoToMovie = { movieId ->
                                    navigator.push(getScreenRegistry(SharedScreen.Movie(movieId)))
                                }
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp).windowInsetsPadding(
                            WindowInsets.safeDrawing
                                .only(WindowInsetsSides.Top)
                                .asPaddingValues()
                                .calculateTopPadding()
                        ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = { navigator.pop() }
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                modifier = Modifier.size(24.dp),
                                tint = primaryWhite,
                                contentDescription = null,
                            )
                        }
                        if (state.state == ScreenState.DEFAULT) {
                            IconButton(
                                onClick = { screenModel::handleAction.invoke(OnToggleFavoriteMovie) }
                            ) {
                                Icon(
                                    Icons.Outlined.Star,
                                    modifier = Modifier.size(24.dp),
                                    tint = state.starColor,
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
