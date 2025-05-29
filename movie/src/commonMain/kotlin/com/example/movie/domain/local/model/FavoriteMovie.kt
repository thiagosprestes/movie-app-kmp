package com.example.movie.domain.local.model

data class FavoriteMovie(
    val id: Long,
    val title: String,
    val posterPath: String? = null
)
