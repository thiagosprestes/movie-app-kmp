package com.example.home.domain.repository

import com.example.core.data.model.HomeMovie
import com.example.core.presentation.model.ShowUiModel

interface HomeRepository {
    suspend fun getNowPlaying(): List<HomeMovie>

    suspend fun getTrending(): List<HomeMovie>

    suspend fun getUpcoming(): List<HomeMovie>

    suspend fun getOnTheAir(): List<ShowUiModel>

    suspend fun getPopular(): List<ShowUiModel>

    suspend fun getTopRated(): List<ShowUiModel>
}