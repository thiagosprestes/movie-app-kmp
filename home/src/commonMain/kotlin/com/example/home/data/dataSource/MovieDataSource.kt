package com.example.home.data.dataSource

import data.model.Movie.MovieResponse

interface MovieDataSource {
    suspend fun getNowPlaying(): MovieResponse

    suspend fun getTrending(): MovieResponse

    suspend fun getUpcoming(): MovieResponse
}