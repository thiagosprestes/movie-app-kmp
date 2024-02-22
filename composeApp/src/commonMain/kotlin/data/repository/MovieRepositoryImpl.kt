package data.repository

import data.api.ApiResponse
import data.model.MovieResponse
import data.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieRepositoryImpl(
    private val httpClient: HttpClient
) : MovieRepository {
    override suspend fun getNowPlaying(): Flow<ApiResponse<MovieResponse>> {
        val apiCall = safeApiCall {
            val response = httpClient.get("movie/popular")
            response.body<MovieResponse>()
        }

        return flowOf(apiCall)
    }
}