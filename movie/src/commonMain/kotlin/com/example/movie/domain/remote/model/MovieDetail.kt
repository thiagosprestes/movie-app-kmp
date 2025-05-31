package com.example.movie.domain.remote.model

import com.example.core.data.model.movieDetail.Genre

data class MovieDetail(
    val id: Long = 0L,
    val backdropPath: String? = null,
    val posterPath: String? = null,
    val genres: List<Genre> = emptyList(),
    val originalTitle: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val runtime: Int = 0,
    val title: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
)