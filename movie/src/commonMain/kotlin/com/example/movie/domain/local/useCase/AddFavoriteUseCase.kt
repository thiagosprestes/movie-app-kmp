package com.example.movie.domain.local.useCase

import com.example.movie.domain.local.model.FavoriteMovie
import com.example.movie.domain.local.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AddFavoriteUseCase {
    suspend operator fun invoke(movie: FavoriteMovie): Flow<Unit>
}

class AddFavoriteUseCaseImpl(
    private val repository: FavoritesRepository
) : AddFavoriteUseCase {
    override suspend fun invoke(movie: FavoriteMovie) = flow {
        val response = repository.addFavorite(movie)
        emit(response)
    }
}