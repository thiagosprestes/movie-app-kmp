package com.example.search.data.datasource

import com.example.core.data.model.movie.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SearchDataSourceImpl(
    private val httpClient: HttpClient
) : SearchDataSource {
    override suspend fun getSearchItems(query: String): MovieResponse {
        val response = httpClient.get("search/movie?query=$query")
        return response.body()
    }
}