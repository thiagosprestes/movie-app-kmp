package data.repository.MovieRepository

import com.example.core.data.model.movieCredits.MovieCreditsResponse
import com.example.core.data.model.movieDetail.MovieDetailResponse
import com.example.network.utils.ApiResponse
import data.model.Movie.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getDetails(id: Int): Flow<ApiResponse<MovieDetailResponse>>
    suspend fun getCredits(id:Int): Flow<ApiResponse<MovieCreditsResponse>>
    suspend fun getSimilar(id:Int): Flow<ApiResponse<MovieResponse>>
}