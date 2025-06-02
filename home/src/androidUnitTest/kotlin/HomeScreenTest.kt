import app.cash.paparazzi.Paparazzi
import com.example.core.data.model.HomeMovie
import com.example.home.presentation.composables.homeDefault
import com.example.home.presentation.model.HomeScreenSectionUiModel
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `show default state`() = paparazzi.snapshot {
        homeDefault(
            nowPlaying = listOf(
                HomeMovie(
                    id = 1,
                    title = "The Batman",
                    posterPath = "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                    backdropPath = "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                    voteAverage = 8.5,
                    voteCount = 15000
                ),
            ),
            trending = HomeScreenSectionUiModel(
                title = "Trending Movies",
                movies = listOf(
                    HomeMovie(
                        id = 1,
                        title = "Interestelar",
                        posterPath = "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                        backdropPath = "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                        voteAverage = 8.5,
                        voteCount = 15000
                    ),
                ),
            ),
            upcoming = HomeScreenSectionUiModel(
                title = "Upcoming Movies",
                movies = listOf(
                    HomeMovie(
                        id = 2,
                        title = "Dune",
                        posterPath = "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                        backdropPath = "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                        voteAverage = 7.5,
                        voteCount = 12000
                    ),
                    HomeMovie(
                        id = 3,
                        title = "No Time to Die",
                        posterPath = "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                        backdropPath = "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                        voteAverage = 7.0,
                        voteCount = 10000
                    ),
                ),
            ),
        ) {}
    }
}