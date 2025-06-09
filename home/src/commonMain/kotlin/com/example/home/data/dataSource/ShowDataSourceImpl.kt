package com.example.home.data.dataSource

import com.example.core.data.model.show.ShowResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ShowDataSourceImpl(
    private val httpClient: HttpClient
) : ShowDataSource {
    override suspend fun getOnTheAir(): ShowResponse {
        val response = httpClient.get("tv/on_the_air")
        return response.body()
    }

    override suspend fun getPopular(): ShowResponse {
        val response = httpClient.get("tv/popular")
        return response.body()
    }

    override suspend fun getTopRated(): ShowResponse {
        val response = httpClient.get("tv/top_rated")
        return response.body()
    }
}