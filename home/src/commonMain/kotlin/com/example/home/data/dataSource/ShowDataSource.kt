package com.example.home.data.dataSource

import com.example.core.data.model.show.ShowResponse

interface ShowDataSource {
    suspend fun getOnTheAir(): ShowResponse
    suspend fun getPopular(): ShowResponse
    suspend fun getTopRated(): ShowResponse
}