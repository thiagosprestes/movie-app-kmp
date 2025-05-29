package com.example.movie.data.local.repository

import com.example.database.Database
import com.example.movie.domain.local.model.FavoriteMovie
import com.example.movie.domain.local.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val database: Database
) : FavoritesRepository {

    override suspend fun addFavorite(movie: FavoriteMovie) {
        database.databaseQueries.addFavorite(
            id = movie.id,
            title = movie.title,
            posterPath = movie.posterPath
        )
    }

    override suspend fun verifyFavorite(id: Long): Boolean {
        val response = database.databaseQueries.isFavorite(id).executeAsOneOrNull()
        return response != null
    }

    override suspend fun removeFavorite(id: Long) {
        database.databaseQueries.removeFavorite(id)
    }
}