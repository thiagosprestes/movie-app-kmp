package com.example.favorites.presentation

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
import com.example.favorites.presentation.composables.Default
import com.example.navigation.SharedScreen
import com.example.navigation.utils.getScreenRegistry

object FavoritesScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<FavoritesScreenModel>()
        val state by screenModel.state.collectAsState()
        val screenState = state.state

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            screenModel.getFavorites()
        }

        when (screenState) {
            ScreenState.LOADING -> Loading()
            ScreenState.ERROR -> Error(onRetry = { screenModel.getFavorites() })
            ScreenState.DEFAULT -> Default(favorites = state.favorites, onNavigate = {
                navigator.push(
                    getScreenRegistry(
                        SharedScreen.Movie(it)
                    )
                )
            })
        }
    }
}