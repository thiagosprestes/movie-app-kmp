package com.example.favorites.domain.mapper

import com.example.Favorites
import com.example.favorites.data.model.FavoriteMovie

fun List<Favorites>.toFavoriteMovie() = map {
    FavoriteMovie(
        id = it.id.toInt(),
        posterPath = it.posterPath,
        title = it.title
    )
}