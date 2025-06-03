import app.cash.paparazzi.Paparazzi
import com.example.core.data.model.ScreenState
import com.example.favorites.data.model.FavoriteMovie
import com.example.favorites.presentation.composables.favoritesScreenContent
import com.example.favorites.presentation.model.FavoritesState
import org.junit.Rule
import kotlin.test.Test

class FavoritesScreenTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `show default state`() = paparazzi.snapshot {
        favoritesScreenContent(
            state = FavoritesState(
                state = ScreenState.DEFAULT,
                favorites = listOf(
                    FavoriteMovie(
                        id = 1,
                        title = "The Dark Knight",
                        posterPath = "https://image.tmdb.org/t/p/w500/1hRoyzDtpgMU7Dz4JG3jJs2mL4.jpg",
                    )
                ),
                pageTitle = "Favoritos",
                emptyListMessage = "Você ainda não tem favoritos"
            )
        ) {}
    }
}