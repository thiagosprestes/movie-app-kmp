package data.repository.HomeRepository

import com.example.core.data.model.Upcoming.UpcomingResponse
import com.example.core.data.model.trending.TrendingResponse
import data.api.ApiResponse
import data.model.Movie.MovieResponse
import data.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HomeRepositoryImpl(
    private val httpClient: HttpClient
) : HomeRepository {
    override suspend fun getNowPlaying(): Flow<ApiResponse<MovieResponse>> {
        val apiCall = safeApiCall {
            val response = httpClient.get("movie/now_playing")
            response.body<MovieResponse>()
        }

        return flowOf(apiCall)
    }

    override suspend fun getTrending(): Flow<ApiResponse<TrendingResponse>> {
        val apiCall = safeApiCall {
            val response = httpClient.get("trending/movie/week")
            response.body<TrendingResponse>()
        }

        return flowOf(apiCall)
    }

    override suspend fun getUpcoming(): Flow<ApiResponse<UpcomingResponse>> {
        val apiCall = safeApiCall {
            val response = httpClient.get("movie/upcoming")
            response.body<UpcomingResponse>()
        }

        return flowOf(apiCall)
    }
}