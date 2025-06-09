package com.example.home.data.repository

import com.example.core.data.model.HomeMovie
import com.example.core.domain.mapper.toMovie
import com.example.core.domain.mapper.toShowUiModel
import com.example.core.presentation.model.ShowUiModel
import com.example.home.data.dataSource.MovieDataSource
import com.example.home.data.dataSource.ShowDataSource
import com.example.home.domain.repository.HomeRepository

const val MAX_ITEMS = 5

class HomeRepositoryImpl(
    private val movieDataSource: MovieDataSource,
    private val showDataSource: ShowDataSource,
) : HomeRepository {
    override suspend fun getNowPlaying(): List<HomeMovie> {
        val response = movieDataSource.getNowPlaying()
        return response.results.take(MAX_ITEMS).toMovie()
    }

    override suspend fun getTrending(): List<HomeMovie> {
        val response = movieDataSource.getTrending()
        return response.results.take(MAX_ITEMS).toMovie()
    }

    override suspend fun getUpcoming(): List<HomeMovie> {
        val response = movieDataSource.getUpcoming()
        return response.results.take(MAX_ITEMS).toMovie()
    }

    override suspend fun getOnTheAir(): List<ShowUiModel> {
        val response = showDataSource.getOnTheAir()
        return response.results.take(MAX_ITEMS).toShowUiModel()
    }

    override suspend fun getPopular(): List<ShowUiModel> {
        val response = showDataSource.getPopular()
        return response.results.take(MAX_ITEMS).toShowUiModel()
    }

    override suspend fun getTopRated(): List<ShowUiModel> {
        val response = showDataSource.getTopRated()
        return response.results.take(MAX_ITEMS).toShowUiModel()
    }
}