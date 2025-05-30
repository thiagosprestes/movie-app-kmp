package com.example.movie.presentation

internal sealed class MovieScreenActions

internal class OnInitMovieScreen(
    val id: Long
) : MovieScreenActions()

internal object OnToggleFavoriteMovie : MovieScreenActions()