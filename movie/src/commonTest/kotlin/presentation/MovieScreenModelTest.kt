package presentation

import com.example.core.data.model.ApiResponse
import com.example.core.data.model.HomeMovie
import com.example.core.data.model.ScreenState
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.domain.local.useCase.AddFavoriteUseCase
import com.example.movie.domain.local.useCase.RemoveFavoriteUseCase
import com.example.movie.domain.remote.model.Movie
import com.example.movie.domain.remote.model.MovieCast
import com.example.movie.domain.remote.useCase.GetMovieUseCase
import com.example.movie.presentation.MovieScreenModel
import com.example.movie.presentation.OnInitMovieScreen
import com.example.movie.presentation.model.MovieCasting
import com.example.movie.presentation.model.MovieDetails
import com.example.movie.presentation.model.MovieHeader
import com.example.movie.presentation.model.MovieSimilar
import com.example.movie.presentation.model.MovieState
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MovieScreenModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun buildFakeMovieState() = MovieState(
        state = ScreenState.DEFAULT,
        id = 0,
        header = MovieHeader(
            isFavorite = false,
            starColor = primaryWhite,
            backdropPath = "",
            posterPath = "",
            title = "",
            genres = "",
            rating = 0.0,
            runtime = "",
            releaseDate = "",
            voteAverage = 0.0,
            voteCount = 0,
        ),
        details = MovieDetails(
            descriptionTitle = "Fake Movie Title",
            descriptionText = "This is a fake movie description for testing purposes."
        ),
        casting = MovieCasting(
            title = "Cast",
            characters = listOf(
                MovieCast(
                    id = 1,
                    profilePath = "https://example.com/john-doe-profile.jpg",
                    name = "John Doe",
                ),
                MovieCast(
                    id = 2,
                    profilePath = "https://example.com/john-doe-profile.jpg",
                    name = "John Doe",
                )
            )
        ),
        similar = MovieSimilar(
            title = "Similar Movies",
            movies = listOf(
                HomeMovie(
                    id = 1,
                    title = "Similar Movie 1",
                    posterPath = "https://example.com/similar-movie-1.jpg",
                    backdropPath = "https://example.com/similar-movie-1-backdrop.jpg",
                    voteAverage = 8.5,
                    voteCount = 1000,
                ),
            )
        ),
    )

    private fun buildFakeMovie() = Movie(
        id = 0,
        header = MovieHeader(
            isFavorite = false,
            starColor = primaryWhite,
            backdropPath = "",
            posterPath = "",
            title = "",
            genres = "",
            rating = 0.0,
            runtime = "",
            releaseDate = "",
            voteAverage = 0.0,
            voteCount = 0,
        ),
        details = MovieDetails(
            descriptionTitle = "Fake Movie Title",
            descriptionText = "This is a fake movie description for testing purposes."
        ),
        casting = MovieCasting(
            title = "Cast",
            characters = listOf(
                MovieCast(
                    id = 1,
                    profilePath = "https://example.com/john-doe-profile.jpg",
                    name = "John Doe",
                ),
                MovieCast(
                    id = 2,
                    profilePath = "https://example.com/john-doe-profile.jpg",
                    name = "John Doe",
                )
            )
        ),
        similar = MovieSimilar(
            title = "Similar Movies",
            movies = listOf(
                HomeMovie(
                    id = 1,
                    title = "Similar Movie 1",
                    posterPath = "https://example.com/similar-movie-1.jpg",
                    backdropPath = "https://example.com/similar-movie-1-backdrop.jpg",
                    voteAverage = 8.5,
                    voteCount = 1000,
                ),
            )
        ),
    )

    @Test
    fun `Given a call on movie use case when init the screen then set the state`() = runTest(testDispatcher) {
        val getMovieUseCase = mock<GetMovieUseCase>()
        val addFavoriteUseCase = mock<AddFavoriteUseCase>()
        val removeFavoriteUseCase = mock<RemoveFavoriteUseCase>()

        val screenModel = MovieScreenModel(
            getMovieUseCase = getMovieUseCase,
            addFavoriteUseCase = addFavoriteUseCase,
            removeFavoriteUseCase = removeFavoriteUseCase,
        )

        val fakeMovie = buildFakeMovie()
        val expectedState = buildFakeMovieState()

        everySuspend { getMovieUseCase(id = 0) } returns flowOf(
            ApiResponse.Success(fakeMovie)
        )

        screenModel.handleAction(OnInitMovieScreen(id = 0))
        advanceUntilIdle()

        assertEquals(expectedState, screenModel.state.value)
    }
}