package com.example.movie.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.movie.presentation.MovieScreen
import com.example.navigation.SharedScreen

val movieNavModule = screenModule {
    register<SharedScreen.Movie> { MovieScreen(it.movieId) }
}