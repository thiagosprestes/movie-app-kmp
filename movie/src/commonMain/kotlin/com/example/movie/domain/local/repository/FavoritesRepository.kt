package com.example.movie.domain.local.repository

import com.example.movie.domain.local.model.FavoriteMovie

interface FavoritesRepository {
    suspend fun addFavorite(movie: FavoriteMovie)
    suspend fun verifyFavorite(id: Long): Boolean
    suspend fun removeFavorite(id: Long)
}