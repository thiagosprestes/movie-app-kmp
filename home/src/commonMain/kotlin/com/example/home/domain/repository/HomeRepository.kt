package com.example.home.domain.repository

import com.example.core.data.model.HomeMovie

interface HomeRepository {
    suspend fun getNowPlaying(): List<HomeMovie>

    suspend fun getTrending(): List<HomeMovie>

    suspend fun getUpcoming(): List<HomeMovie>
}