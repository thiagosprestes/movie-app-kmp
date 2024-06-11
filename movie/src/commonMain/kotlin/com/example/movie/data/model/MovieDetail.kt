package com.example.movie.data.model

import com.example.core.data.model.movieDetail.Genre

data class MovieDetail(
    val id: Int,
    val backdropPath: String? = null,
    val genres: List<Genre>,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)
