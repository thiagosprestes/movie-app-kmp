import app.cash.paparazzi.Paparazzi
import com.example.core.data.model.HomeMovie
import com.example.movie.domain.remote.model.MovieCast
import com.example.movie.domain.remote.model.MovieGenre
import com.example.movie.domain.remote.model.MovieGenres
import com.example.movie.presentation.composables.defaultState.MovieScreenDefaultStateParams
import com.example.movie.presentation.composables.defaultState.movieScreenDefaultState
import com.example.movie.presentation.model.MovieCasting
import com.example.movie.presentation.model.MovieDetails
import com.example.movie.presentation.model.MovieHeader
import com.example.movie.presentation.model.MovieSimilar
import org.junit.Rule
import kotlin.test.Test

class MovieScreenTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `show default state`() = paparazzi.snapshot {
        movieScreenDefaultState(
            params = MovieScreenDefaultStateParams(
                header = MovieHeader(
                    backdropPath = "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                    title = "The Batman",
                    posterPath = "https://image.tmdb.org/t/p/w500/9f4d2c8b3e6a0f5b7c8d1c4e2f3.jpg",
                    voteAverage = 8f,
                    releaseDate = "04/03/2022",
                    isFavorite = false,
                    runtime = "2h 55m",
                ),
                details = MovieDetails(
                    descriptionTitle = "Descrição",
                    descriptionText = "When the Riddler, a sadistic serial killer, begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.",
                ),
                casting = MovieCasting(
                    title = "Elenco",
                    characters = listOf(
                        MovieCast(
                            id = 1,
                            name = "Robert Pattinson",
                            profilePath = "https://image.tmdb.org/t/p/w500/9f4d2c8b3e6a0f5b7c8d1c4e2f3.jpg"
                        ),
                        MovieCast(
                            id = 2,
                            name = "Zoë Kravitz",
                            profilePath = "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg"
                        )
                    )
                ),
                genres = MovieGenres(
                    title = "Gêneros",
                    items = listOf(
                        MovieGenre(id = 1, name = "Action"),
                        MovieGenre(id = 2, name = "Crime"),
                        MovieGenre(id = 3, name = "Drama")
                    )
                ),
                similar = MovieSimilar(
                    title = "Filmes semelhantes",
                    movies = listOf(
                        HomeMovie(
                            id = 1,
                            title = "The Dark Knight",
                            posterPath = "https://image.tmdb.org/t/p/w500/1hRoyzDtpgMU7Dz4JG3jJs2mL4.jpg",
                            backdropPath = "",
                            voteAverage = 0.0,
                            voteCount = 0,
                        ),
                        HomeMovie(
                            id = 2,
                            title = "Joker",
                            posterPath = "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                            backdropPath = "",
                            voteAverage = 0.0,
                            voteCount = 0,
                        )
                    ),
                )
            ) {}
        )
    }
}