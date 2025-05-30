package com.example.movie.domain.remote.model

import com.example.core.data.model.movieDetail.Genre

data class MovieDetail(
    val id: Long,
    val backdropPath: String? = null,
    val posterPath: String? = null,
    val genres: List<Genre>,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)