package data.repository.SearchRepository

import com.example.network.utils.ApiResponse
import data.model.Movie.MovieResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchItems(query: String): Flow<ApiResponse<MovieResponse>>
}