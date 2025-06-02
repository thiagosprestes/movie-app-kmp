package com.example.home.domain.mapper

import com.example.core.data.model.HomeMovie
import com.example.home.presentation.model.HomeScreenSectionUiModel

fun List<HomeMovie>.toHomeScreenSectionUiModel(title: String): HomeScreenSectionUiModel =
    HomeScreenSectionUiModel(
        title = title,
        movies = this
    )