package com.example.favorites.presentation.model

import com.example.core.data.model.ScreenState
import com.example.favorites.data.model.FavoriteMovie

data class FavoritesState(
    val state: ScreenState = ScreenState.LOADING,
    val favorites: List<FavoriteMovie> = emptyList(),
    val pageTitle: String = "Favoritos",
    val emptyListMessage: String = "Você ainda não tem favoritos",
)