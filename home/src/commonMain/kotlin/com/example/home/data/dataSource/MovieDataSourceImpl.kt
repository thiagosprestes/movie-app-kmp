package com.example.home.data.dataSource

import com.example.core.data.model.movie.MovieResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MovieDataSourceImpl(
    private val httpClient: HttpClient
) : MovieDataSource {
    override suspend fun getNowPlaying(): MovieResponse {
        val response = httpClient.get("movie/now_playing")
        return response.body()
    }

    override suspend fun getTrending(): MovieResponse {
        val response = httpClient.get("trending/movie/week")
        return response.body()
    }

    override suspend fun getUpcoming(): MovieResponse {
        val response = httpClient.get("movie/upcoming")
        return response.body()
    }
}