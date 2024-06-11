package com.example.movie.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.data.model.ScreenState
import com.example.core.presentation.composables.Error
import com.example.core.presentation.composables.Loading
import com.example.core.presentation.theme.backgroundEnd
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.presentation.composables.Default
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

data class MovieScreen(val id: Int) : Screen {
    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<MovieScreenModel>()
        val state by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        suspend fun onGetData() {
            screenModel.getDetails(id)
            screenModel.getCast(id)
            screenModel.getSimilar(id)
        }

        fun onInit() {
            GlobalScope.launch {
                onGetData()
            }
        }

        LaunchedEffect(key1 = screenModel) {
            onGetData()
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
                                similar = state.similar
                            )
                        }
                    }
                    IconButton(onClick = { navigator.pop() }) {
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
