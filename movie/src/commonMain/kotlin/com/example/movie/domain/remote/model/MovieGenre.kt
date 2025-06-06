package com.example.movie.domain.remote.model

data class MovieGenre(
    val id: Int = 0,
    val name: String = ""
)

data class MovieGenres(
    val title: String = "",
    val items: List<MovieGenre> = emptyList(),
)