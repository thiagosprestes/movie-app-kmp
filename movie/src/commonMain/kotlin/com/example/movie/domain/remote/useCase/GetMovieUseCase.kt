package com.example.movie.domain.remote.useCase

import com.example.movie.domain.local.repository.FavoritesRepository
import com.example.movie.domain.remote.model.Movie
import com.example.movie.domain.remote.repository.MovieRepository
import com.example.network.utils.ApiResponse
import com.example.network.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetMovieUseCase {
    suspend operator fun invoke(id: Long): Flow<ApiResponse<Movie>>
}

class GetMovieUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val favoritesRepository: FavoritesRepository
) : GetMovieUseCase {
    override suspend fun invoke(id: Long): Flow<ApiResponse<Movie>> = flow {
        val response = safeApiCall {
            val detailsResponse = movieRepository.getDetails(id)
            val castResponse = movieRepository.getCast(id)
            val similarResponse = movieRepository.getSimilar(id)
            val favoritesResponse = favoritesRepository.verifyFavorite(id)

            Movie(
                details = detailsResponse,
                isFavorite = favoritesResponse,
                cast = castResponse,
                similar = similarResponse
            )
        }
        emit(response)
    }
}