package com.example.movie.data.remote.datasource

import com.example.core.data.model.movieCredits.MovieCreditsResponse
import com.example.core.data.model.movieDetail.MovieDetailResponse
import data.model.Movie.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieDataSourceImpl(
    private val httpClient: HttpClient
) : MovieDataSource {
    override suspend fun getDetails(id: Int): MovieDetailResponse {
        val response = httpClient.get("movie/$id")
        return response.body()
    }

    override suspend fun getCredits(id: Int): MovieCreditsResponse {
        val response = httpClient.get("movie/$id/credits")
        return response.body()
    }

    override suspend fun getSimilar(id: Int): MovieResponse {
        val response = httpClient.get("movie/$id/similar")
        return response.body()
    }
}