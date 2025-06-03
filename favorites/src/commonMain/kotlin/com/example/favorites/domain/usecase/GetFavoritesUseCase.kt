package com.example.favorites.domain.usecase

import com.example.database.model.DatabaseResponse
import com.example.favorites.data.model.FavoriteMovie
import com.example.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetFavoritesUseCase {
    operator fun invoke(): Flow<DatabaseResponse<List<FavoriteMovie>>>
}

class GetFavoritesUseCaseImpl(
    private val repository: FavoritesRepository
) : GetFavoritesUseCase {
    override fun invoke(): Flow<DatabaseResponse<List<FavoriteMovie>>> = flow {
        emit(DatabaseResponse.Loading)
        try {
            val response = repository.getFavorites()
            emit(DatabaseResponse.Success(response))
        } catch (e: Exception) {
            emit(DatabaseResponse.Error(e.toString()))
        }
    }
}