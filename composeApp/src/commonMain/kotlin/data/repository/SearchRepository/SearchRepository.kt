package data.repository.SearchRepository

import data.api.ApiResponse
import data.model.Movie.MovieResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchItems(query: String): Flow<ApiResponse<MovieResponse>>
}