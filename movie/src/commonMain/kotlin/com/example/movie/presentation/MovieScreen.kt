package com.example.movie.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

data class MovieScreen(val id: Int) : Screen {
    override val key = uniqueScreenKey

    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<MovieScreenModel>()
        val state by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        fun onInit() {
            GlobalScope.launch {
                screenModel.getDetails(id)
                screenModel.getCast(id)
                screenModel.getSimilar(id)
            }
        }

        LaunchedEffect(key1 = screenModel) {
            onInit()
        }

        Column(Modifier.background(backgroundEnd).fillMaxSize()) {
            Column {
                Box {
                    when (state.state) {
                        ScreenState.LOADING -> Loading()
                        ScreenState.ERROR -> Error(onRetry = { onInit() })
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
                    IconButton(
                        modifier = Modifier.windowInsetsPadding(
                            WindowInsets.safeDrawing
                                .only(WindowInsetsSides.Top)
                                .asPaddingValues()
                                .calculateTopPadding()
                        ),
                        onClick = { navigator.pop() }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(24.dp),
                            tint = primaryWhite,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}
