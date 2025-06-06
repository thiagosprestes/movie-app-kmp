package com.example.movie.domain.remote.model

data class MovieCast(
    val id: Int,
    val profilePath: String? = null,
    val name: String,
    val character: String,
)