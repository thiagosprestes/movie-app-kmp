package com.example.movie.domain.local.usecase

import com.example.movie.domain.local.repository.FavoritesRepository
import kotlinx.coroutines.flow.flow

class AddFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(id: Int, title: String, posterPath: String?) = flow {
        val response = repository.addFavorite(id, title, posterPath)
        emit(response)
    }
}