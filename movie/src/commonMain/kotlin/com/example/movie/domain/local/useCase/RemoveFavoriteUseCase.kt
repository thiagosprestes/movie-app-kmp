package com.example.movie.domain.local.useCase

import com.example.movie.domain.local.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface RemoveFavoriteUseCase {
    suspend operator fun invoke(id: Long): Flow<Unit>
}

class RemoveFavoriteUseCaseImpl(
    private val repository: FavoritesRepository
) : RemoveFavoriteUseCase {
    override suspend fun invoke(id: Long) = flow {
        val response = repository.removeFavorite(id)
        emit(response)
    }
}