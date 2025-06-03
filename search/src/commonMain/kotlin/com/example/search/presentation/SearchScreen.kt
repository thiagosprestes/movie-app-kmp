package com.example.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.data.model.ScreenState
import com.example.core.presentation.composables.Error
import com.example.core.presentation.composables.Loading
import com.example.core.presentation.theme.backgroundGradient
import com.example.core.utils.windowInsetsPadding
import com.example.navigation.SharedScreen
import com.example.navigation.utils.getScreenRegistry
import com.example.search.presentation.composables.searchScreenDefaultState
import com.example.search.presentation.composables.searchScreenHeader
import kotlinx.coroutines.delay

object SearchScreen : Screen {
    @Composable
    override fun Content() {
        var textInput by remember { mutableStateOf("") }

        val screenModel = koinScreenModel<SearchScreenModel>()
        val state by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = textInput) {
            if (textInput.isBlank()) return@LaunchedEffect

            delay(2000)

            screenModel.getSearchItems(textInput)
        }

        Column(
            Modifier
                .background(
                    backgroundGradient
                )
                .fillMaxSize()
                .padding(16.dp)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing
                        .only(WindowInsetsSides.Top)
                        .asPaddingValues()
                        .calculateTopPadding()
                )
        ) {
            searchScreenHeader {
                screenModel.getSearchItems(textInput)
            }
            when (state.state) {
                ScreenState.LOADING -> Loading()
                ScreenState.ERROR -> Error(onRetry = {
                    screenModel.handleOnRetry(
                        textInput
                    )
                })

                ScreenState.DEFAULT -> searchScreenDefaultState(
                    movies = state.results,
                    hasNoResultsFound = state.isEmptyResult
                ) {
                    navigator.push(getScreenRegistry(SharedScreen.Movie(it)))
                }
            }
        }
    }
}

