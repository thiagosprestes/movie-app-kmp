package data.repository.SearchRepository

import data.api.ApiResponse
import data.model.Movie.MovieResponse
import data.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SearchRepositoryImpl(
    private val httpClient: HttpClient
) : SearchRepository {
    override suspend fun getSearchItems(query: String): Flow<ApiResponse<MovieResponse>> {
        val apiCall = safeApiCall {
            val response = httpClient.get("search/movie?query=$query")
            response.body<MovieResponse>()
        }

        return flowOf(apiCall)
    }
}