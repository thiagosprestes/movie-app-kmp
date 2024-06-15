package com.example.movie.domain.local.usecase

import com.example.movie.domain.local.repository.FavoritesRepository
import kotlinx.coroutines.flow.flow

class RemoveFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(id: Int) = flow {
        val response = repository.removeFavorite(id)
        emit(response)
    }
}