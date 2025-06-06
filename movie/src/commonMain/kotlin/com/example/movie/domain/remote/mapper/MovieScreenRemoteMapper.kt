package com.example.movie.domain.remote.mapper

import androidx.compose.ui.graphics.Color
import com.example.core.data.model.HomeMovie
import com.example.core.data.model.movieCredits.Cast
import com.example.core.data.model.movieDetail.Genre
import com.example.core.data.model.movieDetail.MovieDetailResponse
import com.example.core.domain.mapper.toUrl
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.domain.remote.model.MovieCast
import com.example.movie.domain.remote.model.MovieDetail
import com.example.movie.domain.remote.model.MovieGenre
import com.example.movie.domain.remote.model.MovieGenres
import com.example.movie.domain.strings.MovieScreenStrings
import com.example.movie.presentation.model.MovieCasting
import com.example.movie.presentation.model.MovieDetails
import com.example.movie.presentation.model.MovieHeader
import com.example.movie.presentation.model.MovieSimilar

private val strings = MovieScreenStrings()

fun List<Cast>.toMovieCast() = map {
    MovieCast(
        id = it.id,
        profilePath = it.profilePath.toUrl(),
        name = it.name
    )
}

fun MovieDetailResponse.toMovieDetail() = MovieDetail(
    id = this.id,
    backdropPath = this.backdropPath.toUrl(),
    posterPath = this.posterPath.toUrl(),
    genres = this.genres,
    originalTitle = this.originalTitle,
    overview = this.overview,
    releaseDate = this.releaseDate,
    runtime = this.runtime,
    title = this.title,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun MovieDetail.toMovieHeader(
    isFavorite: Boolean,
): MovieHeader {
    val starColor = if (isFavorite) Color.Yellow else primaryWhite

    return MovieHeader(
        isFavorite = isFavorite,
        starColor = starColor,
        backdropPath = this.backdropPath ?: "",
        posterPath = this.posterPath ?: "",
        title = this.title,
        rating = this.voteAverage,
        runtime = strings.runtime(this.runtime),
        releaseDate = strings.releaseDate(this.releaseDate),
        voteAverage = this.voteAverage.toFloat(),
    )
}

fun MovieDetail.toMovieDetail(): MovieDetails = MovieDetails(
    descriptionTitle = strings.description,
    descriptionText = this.overview,
)

fun List<MovieCast>.toCasting(): MovieCasting =
    MovieCasting(
        title = strings.casting,
        characters = this,
    )

fun List<HomeMovie>.toSimilar(): MovieSimilar = MovieSimilar(
    title = strings.similarMovies,
    movies = this,
)

private fun List<Genre>.toMovieGenre(): List<MovieGenre> = this.map {
    MovieGenre(id = it.id, name = it.name)
}

fun List<Genre>.toMovieGenres(): MovieGenres = MovieGenres(
    title = strings.genres,
    items = this.toMovieGenre()
)