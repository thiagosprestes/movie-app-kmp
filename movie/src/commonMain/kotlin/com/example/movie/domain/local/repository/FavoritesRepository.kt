package com.example.movie.domain.local.repository

interface FavoritesRepository {
    suspend fun addFavorite(id: Int, title: String, posterPath: String? = null)
    suspend fun verifyFavorite(id: Int): Boolean
    suspend fun removeFavorite(id: Int)
}