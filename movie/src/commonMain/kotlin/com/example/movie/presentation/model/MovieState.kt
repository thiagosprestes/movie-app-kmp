package com.example.movie.presentation.model

import androidx.compose.ui.graphics.Color
import com.example.core.data.model.HomeMovie
import com.example.core.data.model.ScreenState
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.domain.remote.model.MovieCast
import com.example.movie.domain.remote.model.MovieDetail

data class MovieState(
    val id: Long = 0,
    val state: ScreenState = ScreenState.LOADING,
    val details: MovieDetail? = null,
    val isFavorite: Boolean = false,
    val starColor:  Color = primaryWhite,
    val cast: List<MovieCast> = emptyList(),
    val similar: List<HomeMovie> = emptyList()
)