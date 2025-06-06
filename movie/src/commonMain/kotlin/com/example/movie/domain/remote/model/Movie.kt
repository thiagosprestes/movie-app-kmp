package com.example.movie.domain.remote.model

import com.example.movie.presentation.model.MovieCasting
import com.example.movie.presentation.model.MovieDetails
import com.example.movie.presentation.model.MovieHeader
import com.example.movie.presentation.model.MovieSimilar

data class Movie(
    val id: Long = 0,
    val header: MovieHeader = MovieHeader(),
    val details: MovieDetails = MovieDetails(),
    val casting: MovieCasting = MovieCasting(),
    val genres: MovieGenres = MovieGenres(),
    val similar: MovieSimilar = MovieSimilar(),
)