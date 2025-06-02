package com.example.movie.domain.remote.useCase

import com.example.core.data.model.ApiResponse
import com.example.movie.domain.local.repository.FavoritesRepository
import com.example.movie.domain.remote.mapper.toCasting
import com.example.movie.domain.remote.mapper.toMovieDetail
import com.example.movie.domain.remote.mapper.toMovieHeader
import com.example.movie.domain.remote.mapper.toSimilar
import com.example.movie.domain.remote.model.Movie
import com.example.movie.domain.remote.repository.MovieRepository
import com.example.network.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

interface GetMovieUseCase {
    suspend operator fun invoke(id: Long): Flow<ApiResponse<Movie>>
}

class GetMovieUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val favoritesRepository: FavoritesRepository
) : GetMovieUseCase {
    override suspend fun invoke(id: Long): Flow<ApiResponse<Movie>> = safeApiCall {
        val detailsResponse = movieRepository.getDetails(id)
        val castResponse = movieRepository.getCast(id)
        val similarResponse = movieRepository.getSimilar(id)
        val isFavorite = favoritesRepository.verifyFavorite(id)

        val header = detailsResponse.toMovieHeader(isFavorite)
        val details = detailsResponse.toMovieDetail()
        val casting = castResponse.toCasting()
        val similar = similarResponse.toSimilar()

        Movie(
            id = id,
            header = header,
            details = details,
            casting = casting,
            similar = similar
        )
    }
}