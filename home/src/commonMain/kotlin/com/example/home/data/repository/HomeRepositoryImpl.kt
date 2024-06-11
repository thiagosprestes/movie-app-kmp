package com.example.home.data.repository

import com.example.core.data.model.HomeMovie
import com.example.core.domain.mapper.toMovie
import com.example.home.data.dataSource.MovieDataSource
import com.example.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val dataSource: MovieDataSource
) : HomeRepository {
    override suspend fun getNowPlaying(): List<HomeMovie> {
        val response = dataSource.getNowPlaying()
        return response.results.take(5).toMovie()
    }

    override suspend fun getTrending(): List<HomeMovie> {
        val response = dataSource.getTrending()
        return response.results.take(5).toMovie()
    }

    override suspend fun getUpcoming(): List<HomeMovie> {
        val response = dataSource.getUpcoming()
        return response.results.take(5).toMovie()
    }
}