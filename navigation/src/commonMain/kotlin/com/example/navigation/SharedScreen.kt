package com.example.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen: ScreenProvider {
    object Home: SharedScreen()
    object Search: SharedScreen()
    data class Movie(val movieId: Int): SharedScreen()
}