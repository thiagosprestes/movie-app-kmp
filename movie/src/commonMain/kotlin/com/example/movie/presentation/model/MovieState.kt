package com.example.movie.presentation.model

import androidx.compose.ui.graphics.Color
import com.example.core.data.model.HomeMovie
import com.example.core.data.model.ScreenState
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.domain.remote.model.MovieCast
import com.example.movie.domain.remote.model.MovieGenres

data class MovieHeader(
    val isFavorite: Boolean = false,
    val starColor: Color = primaryWhite,
    val backdropPath: String = "",
    val posterPath: String = "",
    val title: String = "",
    val rating: Double = 0.0,
    val runtime: String = "",
    val releaseDate: String = "",
    val voteAverage: Float = 0f,
)

data class MovieDetails(
    val descriptionTitle: String = "",
    val descriptionText: String = "",
)

data class MovieCasting(
    val title: String = "",
    val characters: List<MovieCast> = emptyList(),
)

data class MovieSimilar(
    val title: String = "",
    val movies: List<HomeMovie> = emptyList(),
)

data class MovieState(
    val state: ScreenState = ScreenState.LOADING,
    val id: Long = 0,
    val header: MovieHeader = MovieHeader(),
    val details: MovieDetails = MovieDetails(),
    val casting: MovieCasting = MovieCasting(),
    val genres: MovieGenres = MovieGenres(),
    val similar: MovieSimilar = MovieSimilar(),
)