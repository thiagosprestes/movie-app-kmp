package com.example.movie.domain.mapper

import com.example.core.data.model.movieDetail.MovieDetailResponse
import com.example.core.domain.mapper.toUrl
import com.example.movie.data.model.MovieDetail

fun MovieDetailResponse.toMovieDetail() = MovieDetail(
    id = this.id,
    backdropPath = this.backdropPath.toUrl(),
    genres = this.genres,
    originalTitle = this.originalTitle,
    overview = this.overview,
    releaseDate = this.releaseDate,
    runtime = this.runtime,
    title = this.title,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)
