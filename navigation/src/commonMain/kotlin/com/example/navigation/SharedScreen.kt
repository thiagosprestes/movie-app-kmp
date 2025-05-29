package com.example.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    object Home : SharedScreen()
    object Search : SharedScreen()
    object Favorites : SharedScreen()
    data class Movie(val movieId: Long) : SharedScreen()
}