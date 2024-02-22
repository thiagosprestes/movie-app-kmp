package data.repository

import data.api.ApiResponse
import data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlaying(): Flow<ApiResponse<MovieResponse>>
}