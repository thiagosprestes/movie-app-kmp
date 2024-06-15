package com.example.favorites.data.repository

import com.example.database.Database
import com.example.favorites.data.model.FavoriteMovie
import com.example.favorites.domain.mapper.toFavoriteMovie
import com.example.favorites.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val database: Database
) : FavoritesRepository {
    override suspend fun getFavorites(): List<FavoriteMovie> {
        val response = database.databaseQueries.getFavorites().executeAsList()
        return response.toFavoriteMovie()
    }
}