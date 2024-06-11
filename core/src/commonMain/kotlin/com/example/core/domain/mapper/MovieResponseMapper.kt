package com.example.core.domain.mapper

import com.example.core.data.model.HomeMovie
import data.model.Movie.Movie

fun List<Movie>.toMovie(): List<HomeMovie> = map {
    HomeMovie(
        id = it.id,
        title = it.title.toString(),
        posterPath = it.posterPath.toUrl(),
        backdropPath = it.backdropPath.toUrl(),
        voteAverage = it.voteAverage,
        voteCount = it.voteCount
    )
}