package data.repository

import data.model.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieRepositoryImpl(
    private val httpClient: HttpClient
):MovieRepository {
    override suspend fun getNowPlaying(): MovieResponse {
        val response = httpClient.get("movie/now_playing")
        return response.body()
    }
}