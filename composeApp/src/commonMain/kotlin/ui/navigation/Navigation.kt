package ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import ui.screens.Home.HomeScreen
import ui.screens.Movie.MovieScreen

@Composable
fun Navigation() {
    Navigator(HomeScreen) { navigator ->
        SlideTransition(navigator)
    }
}