package com.example.home.domain.mapper

import com.example.core.data.model.HomeMovie
import com.example.core.presentation.model.ShowUiModel
import com.example.home.presentation.model.HomeScreenMovieSectionUiModel
import com.example.home.presentation.model.HomeScreenShowSectionUiModel

fun List<HomeMovie>.toHomeScreenSectionUiModel(title: String): HomeScreenMovieSectionUiModel =
    HomeScreenMovieSectionUiModel(
        title = title,
        movies = this
    )

fun List<ShowUiModel>.toHomeScreenSectionUiModel(title: String): HomeScreenShowSectionUiModel =
    HomeScreenShowSectionUiModel(
        title = title,
        shows = this
    )