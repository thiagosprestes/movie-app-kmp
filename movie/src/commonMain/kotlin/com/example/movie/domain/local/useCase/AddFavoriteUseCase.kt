package com.example.movie.domain.local.useCase

import com.example.movie.domain.local.model.FavoriteMovie
import com.example.movie.domain.local.repository.FavoritesRepository
import kotlinx.coroutines.flow.flow

class AddFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(movie: FavoriteMovie) = flow {
        val response = repository.addFavorite(movie)
        emit(response)
    }
}