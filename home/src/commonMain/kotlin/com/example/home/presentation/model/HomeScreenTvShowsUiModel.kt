package com.example.home.presentation.model

data class HomeScreenTvShowsUiModel(
    val popularTvShows: HomeScreenShowSectionUiModel = HomeScreenShowSectionUiModel(),
    val topRatedTvShows: HomeScreenShowSectionUiModel = HomeScreenShowSectionUiModel(),
    val onTheAirTvShows: HomeScreenShowSectionUiModel = HomeScreenShowSectionUiModel(),
)
