package com.example.favorites.domain.usecase

import com.example.database.model.DatabaseResponse
import com.example.favorites.data.model.FavoriteMovie
import com.example.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFavoritesUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(): Flow<DatabaseResponse<List<FavoriteMovie>>> = flow {
        try {
            val response = repository.getFavorites()
            emit(DatabaseResponse.Success(response))
        } catch (e: Exception) {
            emit(DatabaseResponse.Error(e.toString()))
        }
    }
}