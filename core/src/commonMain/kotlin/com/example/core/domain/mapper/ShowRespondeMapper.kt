package com.example.core.domain.mapper

import com.example.core.data.model.show.Show
import com.example.core.presentation.model.ShowUiModel

fun List<Show>.toShowUiModel(): List<ShowUiModel> = map {
    ShowUiModel(
        id = it.id,
        name = it.name,
        posterPath = it.posterPath.toUrl(),
        backdropPath = it.backdropPath.toUrl(),
        voteAverage = it.voteAverage,
        voteCount = it.voteCount
    )
}