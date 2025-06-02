package com.example.home.presentation.model

import com.example.core.data.model.HomeMovie

data class HomeScreenSectionUiModel(
    val title: String = "",
    val movies: List<HomeMovie> = emptyList(),
)
