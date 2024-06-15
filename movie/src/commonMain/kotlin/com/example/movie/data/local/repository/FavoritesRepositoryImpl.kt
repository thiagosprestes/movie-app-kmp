package com.example.movie.data.local.repository

import com.example.database.Database
import com.example.movie.domain.local.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val database: Database
) : FavoritesRepository {

    override suspend fun addFavorite(id: Int, title: String, posterPath: String?) {
        database.databaseQueries.addFavorite(
            id = id.toLong(),
            title = title,
            posterPath = posterPath
        )
    }

    override suspend fun verifyFavorite(id: Int): Boolean {
        val response = database.databaseQueries.isFavorite(id.toLong()).executeAsOneOrNull()
        return response != null
    }

    override suspend fun removeFavorite(id: Int) {
        database.databaseQueries.removeFavorite(id.toLong())
    }
}