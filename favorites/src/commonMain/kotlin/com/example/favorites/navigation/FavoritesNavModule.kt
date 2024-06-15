package com.example.favorites.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.favorites.presentation.FavoritesScreen
import com.example.navigation.SharedScreen

val favoritesNavModule = screenModule {
    register<SharedScreen.Favorites> { FavoritesScreen }
}