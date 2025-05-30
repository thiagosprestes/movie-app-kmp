package com.example.movie.domain.local.useCase

import com.example.movie.domain.local.repository.FavoritesRepository
import kotlinx.coroutines.flow.flow

class RemoveFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(id: Long) = flow {
        val response = repository.removeFavorite(id)
        emit(response)
    }
}