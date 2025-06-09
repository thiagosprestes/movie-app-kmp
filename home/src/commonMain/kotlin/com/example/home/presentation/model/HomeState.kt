package com.example.home.presentation.model

import com.example.core.data.model.ScreenState

data class HomeState(
    val state: ScreenState = ScreenState.LOADING,
    val selectedType: HomeScreenSelectedOption = HomeScreenSelectedOption.MOVIES,
    val types: List<HomeScreenOptionUiModel> = emptyList(),
    val moviesUiModel: HomeScreenMoviesUiModel = HomeScreenMoviesUiModel(),
    val tvShowsUiModel: HomeScreenTvShowsUiModel = HomeScreenTvShowsUiModel(),
)