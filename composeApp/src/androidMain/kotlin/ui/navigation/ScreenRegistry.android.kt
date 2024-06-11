package ui.navigation

import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.home.navigation.homeNavModule
import com.example.navigation.SharedScreen
import ui.screens.Movie.MovieScreen
import ui.screens.Search.SearchScreen

actual fun screenRegistry() = ScreenRegistry {
    register<SharedScreen.Search> { SearchScreen }
    register<SharedScreen.Movie> { MovieScreen(it.movieId) }
    homeNavModule()
}