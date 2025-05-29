package com.example.home.data.dataSource

import com.example.core.data.model.movie.MovieResponse

interface MovieDataSource {
    suspend fun getNowPlaying(): MovieResponse

    suspend fun getTrending(): MovieResponse

    suspend fun getUpcoming(): MovieResponse
}