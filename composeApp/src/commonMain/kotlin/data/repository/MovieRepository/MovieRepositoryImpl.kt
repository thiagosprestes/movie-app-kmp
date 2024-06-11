package data.repository.MovieRepository

import com.example.core.data.model.movieCredits.MovieCreditsResponse
import com.example.core.data.model.movieDetail.MovieDetailResponse
import data.api.ApiResponse
import data.model.Movie.MovieResponse
import data.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieRepositoryImpl(
    private val httpClient: HttpClient
) : MovieRepository {
    override suspend fun getDetails(id: Int): Flow<ApiResponse<MovieDetailResponse>> {
        val apiCall = safeApiCall {
            val response = httpClient.get("movie/$id")
            response.body<MovieDetailResponse>()
        }

        return flowOf(apiCall)
    }

    override suspend fun getCredits(id: Int): Flow<ApiResponse<MovieCreditsResponse>> {
        val apiCall = safeApiCall {
            val response = httpClient.get("movie/$id/credits")
            response.body<MovieCreditsResponse>()
        }

        return flowOf(apiCall)
    }

    override suspend fun getSimilar(id: Int): Flow<ApiResponse<MovieResponse>> {
        val apiCall = safeApiCall {
            val response = httpClient.get("movie/$id/similar")
            response.body<MovieResponse>()
        }

        return flowOf(apiCall)
    }
}