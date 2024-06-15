package com.example.movie.domain.local.usecase

import com.example.database.model.DatabaseResponse
import com.example.movie.domain.local.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VerifyFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(id: Int): Flow<DatabaseResponse<Boolean>> = flow {
        try {
            emit(DatabaseResponse.Loading)
            val response = repository.verifyFavorite(id)
            emit(DatabaseResponse.Success(response))
        } catch (e: Exception) {
            emit(DatabaseResponse.Error(e.toString()))
        }
    }
}