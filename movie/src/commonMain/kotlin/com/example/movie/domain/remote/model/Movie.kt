package com.example.movie.domain.remote.model

import com.example.core.data.model.HomeMovie

data class Movie(
    val details: MovieDetail? = null,
    val isFavorite: Boolean = false,
    val cast: List<MovieCast> = emptyList(),
    val similar: List<HomeMovie> = emptyList(),
)