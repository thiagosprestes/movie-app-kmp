package com.example.movie.domain.remote.useCase

import androidx.compose.ui.graphics.Color
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.domain.local.repository.FavoritesRepository
import com.example.movie.domain.remote.model.Movie
import com.example.movie.domain.remote.repository.MovieRepository
import com.example.movie.domain.strings.MovieScreenStrings
import com.example.movie.presentation.model.MovieCasting
import com.example.movie.presentation.model.MovieDetails
import com.example.movie.presentation.model.MovieHeader
import com.example.movie.presentation.model.MovieSimilar
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
    private val strings = MovieScreenStrings()

    override suspend fun invoke(id: Long): Flow<ApiResponse<Movie>> = flow {
        val response = safeApiCall {
            val detailsResponse = movieRepository.getDetails(id)
            val castResponse = movieRepository.getCast(id)
            val similarResponse = movieRepository.getSimilar(id)
            val isFavorite = favoritesRepository.verifyFavorite(id)

            val starColor = when {
                isFavorite -> Color.Yellow
                else -> primaryWhite
            }

            val header = MovieHeader(
                isFavorite = isFavorite,
                starColor = starColor,
                backdropPath = detailsResponse.backdropPath ?: "",
                posterPath = detailsResponse.posterPath ?: "",
                title = detailsResponse.title,
                genres = detailsResponse.genres.joinToString { it.name },
                rating = detailsResponse.voteAverage,
                runtime = strings.runtime(detailsResponse.runtime),
                releaseDate = strings.releaseDate(detailsResponse.releaseDate),
                voteAverage = detailsResponse.voteAverage,
                voteCount = detailsResponse.voteCount,
            )

            val details = MovieDetails(
                descriptionTitle = strings.description,
                descriptionText = detailsResponse.overview,
            )

            val casting = MovieCasting(
                title = strings.casting,
                characters = castResponse,
            )

            val similar = MovieSimilar(
                title = strings.similarMovies,
                movies = similarResponse,
            )

            Movie(
                id = id,
                header = header,
                details = details,
                casting = casting,
                similar = similar
            )
        }
        emit(response)
    }
}