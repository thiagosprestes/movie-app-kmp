package com.example.core.data.model

data class HomeMovie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
    val voteCount: Int
)