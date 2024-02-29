package data.repository.MovieRepository

import data.api.ApiResponse
import data.model.Movie.MovieResponse
import data.model.MovieDetail.MovieDetailResponse
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
}