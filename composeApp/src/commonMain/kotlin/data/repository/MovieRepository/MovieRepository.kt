package data.repository.MovieRepository

import data.api.ApiResponse
import data.model.Movie.MovieResponse
import data.model.MovieDetail.MovieDetailResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getDetails(id: Int): Flow<ApiResponse<MovieDetailResponse>>
}