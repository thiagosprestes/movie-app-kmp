package com.example.movie.domain.remote.mapper

import com.example.core.data.model.movieCredits.Cast
import com.example.core.data.model.movieDetail.MovieDetailResponse
import com.example.core.domain.mapper.toUrl
import com.example.movie.domain.remote.model.MovieCast
import com.example.movie.domain.remote.model.MovieDetail

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
