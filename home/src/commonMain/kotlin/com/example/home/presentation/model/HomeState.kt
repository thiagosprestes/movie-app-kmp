package com.example.home.presentation.model

import com.example.core.data.model.HomeMovie
import com.example.core.data.model.ScreenState

data class HomeState(
    val state: ScreenState = ScreenState.LOADING,
    val nowPlayingMovies: List<HomeMovie> = emptyList(),
    val trendingMovies: HomeScreenSectionUiModel = HomeScreenSectionUiModel(),
    val upcomingMovies: HomeScreenSectionUiModel = HomeScreenSectionUiModel(),
)