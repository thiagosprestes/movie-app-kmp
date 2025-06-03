import app.cash.paparazzi.Paparazzi
import com.example.core.data.model.HomeMovie
import com.example.search.presentation.composables.searchScreenDefaultState
import org.junit.Rule
import kotlin.test.Test

class SearchScreenTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `show default state`() = paparazzi.snapshot {
        searchScreenDefaultState(
            movies = listOf(
                HomeMovie(
                    id = 1,
                    title = "Movie 1",
                    posterPath = "path/to/poster1.jpg",
                    backdropPath = "path/to/backdrop1.jpg",
                    voteAverage = 0.0,
                    voteCount = 0
                )
            ),
            hasNoResultsFound = false
        ) {}
    }
}