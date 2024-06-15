package com.example.favorites.domain.repository

import com.example.favorites.data.model.FavoriteMovie

interface FavoritesRepository {
    suspend fun getFavorites(): List<FavoriteMovie>
}