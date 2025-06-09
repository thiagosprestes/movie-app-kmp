import com.example.core.data.model.ApiResponse
import com.example.core.data.model.HomeMovie
import com.example.core.data.model.ScreenState
import com.example.home.domain.useCase.GetHomeUseCase
import com.example.home.presentation.HomeScreenModel
import com.example.home.presentation.model.HomeScreenMovieSectionUiModel
import com.example.home.presentation.model.HomeState
import com.example.home.presentation.model.OnInitHomeScreen
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
class HomeScreenModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val fakeHomeNowPlaying = listOf(
        HomeMovie(
            id = 1,
            title = "Movie 1",
            posterPath = "/path/to/poster1.jpg",
            backdropPath = "/path/to/backdrop1.jpg",
            voteAverage = 0.0,
            voteCount = 0,

            ),
        HomeMovie(
            id = 2,
            title = "Movie 2",
            posterPath = "/path/to/poster2.jpg",
            backdropPath = "/path/to/backdrop2.jpg",
            voteAverage = 0.0,
            voteCount = 0,
        )
    )

    private val fakeHomeTrending = HomeScreenMovieSectionUiModel(
        title = "Trending Movies",
        movies = listOf(
            HomeMovie(
                id = 3,
                title = "Movie 3",
                posterPath = "/path/to/poster3.jpg",
                backdropPath = "/path/to/backdrop3.jpg",
                voteAverage = 0.0,
                voteCount = 0,
            ),
            HomeMovie(
                id = 4,
                title = "Movie 4",
                posterPath = "/path/to/poster4.jpg",
                backdropPath = "/path/to/backdrop4.jpg",
                voteAverage = 0.0,
                voteCount = 0,
            )
        )
    )

    private val fakeHomeUpcoming = HomeScreenMovieSectionUiModel(
        title = "Upcoming Movies",
        movies = listOf(
            HomeMovie(
                id = 5,
                title = "Movie 5",
                posterPath = "/path/to/poster5.jpg",
                backdropPath = "/path/to/backdrop5.jpg",
                voteAverage = 0.0,
                voteCount = 0,
            ),
            HomeMovie(
                id = 6,
                title = "Movie 6",
                posterPath = "/path/to/poster6.jpg",
                backdropPath = "/path/to/backdrop6.jpg",
                voteAverage = 0.0,
                voteCount = 0,
            )
        )
    )

    @Test
    fun `given a call on HomeScreenModel, when the screen is initialized, then it should load the movies`() =
        runTest(testDispatcher) {
            val mockedGetHomeUseCase = mock<GetHomeUseCase>()

            val mockedHomeState = HomeState(
                state = ScreenState.DEFAULT,
                nowPlayingMovies = fakeHomeNowPlaying,
                trendingMovies = fakeHomeTrending,
                upcomingMovies = fakeHomeUpcoming
            )

            val homeScreenModel = HomeScreenModel(
                getHomeUseCase = mockedGetHomeUseCase
            )

            everySuspend { mockedGetHomeUseCase() } returns flowOf(
                ApiResponse.Success(
                    mockedHomeState
                )
            )

            homeScreenModel::handleAction.invoke(OnInitHomeScreen)

            advanceUntilIdle()

            assertEquals(
                expected = mockedHomeState,
                actual = homeScreenModel.state.value
            )
        }
}