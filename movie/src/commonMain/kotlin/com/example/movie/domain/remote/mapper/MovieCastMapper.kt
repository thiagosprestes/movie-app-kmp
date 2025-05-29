package com.example.movie.domain.remote.mapper

import com.example.core.data.model.movieCredits.Cast
import com.example.core.domain.mapper.toUrl
import com.example.movie.domain.remote.model.MovieCast

fun List<Cast>.toMovieCast() = map {
    MovieCast(
        id = it.id,
        profilePath = it.profilePath.toUrl(),
        name = it.name
    )
}