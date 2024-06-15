package com.example.movie.presentation.model

import com.example.core.data.model.HomeMovie
import com.example.core.data.model.ScreenState
import com.example.movie.data.remote.model.MovieCast
import com.example.movie.data.remote.model.MovieDetail

data class MovieState(
    val state: ScreenState = ScreenState.LOADING,
    val details: MovieDetail? = null,
    val isFavorite: Boolean = false,
    val cast: List<MovieCast> = emptyList(),
    val similar: List<HomeMovie> = emptyList()
)