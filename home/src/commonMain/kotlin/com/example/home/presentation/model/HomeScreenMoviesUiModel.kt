package com.example.home.presentation.model

import com.example.core.data.model.HomeMovie

data class HomeScreenMoviesUiModel(
    val nowPlayingMovies: List<HomeMovie> = emptyList(),
    val trendingMovies: HomeScreenMovieSectionUiModel = HomeScreenMovieSectionUiModel(),
    val upcomingMovies: HomeScreenMovieSectionUiModel = HomeScreenMovieSectionUiModel(),
)
