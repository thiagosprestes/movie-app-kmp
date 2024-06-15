package navigation

import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.favorites.navigation.favoritesNavModule
import com.example.home.navigation.homeNavModule
import com.example.movie.navigation.movieNavModule
import com.example.search.navigation.searchNavModule

actual fun screenRegistry() = ScreenRegistry {
    searchNavModule()
    movieNavModule()
    homeNavModule()
    favoritesNavModule()
}