package presentation

import com.example.core.data.model.ApiResponse
import com.example.core.data.model.HomeMovie
import com.example.search.domain.useCase.GetSearchResultsUseCase
import com.example.search.presentation.SearchScreenModel
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
class SearchScreenModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun mockedResults() = listOf(
        HomeMovie(
            id = 1,
            title = "Movie 1",
            posterPath = "poster1.jpg",
            backdropPath = "backdrop1.jpg",
            voteAverage = 0.0,
            voteCount = 0

        ),
        HomeMovie(
            id = 1,
            title = "Movie 1",
            posterPath = "poster1.jpg",
            backdropPath = "backdrop1.jpg",
            voteAverage = 0.0,
            voteCount = 0
        )
    )

    @Test
    fun `Given a call on movies based on query with use case then set the state`() =
        runTest(testDispatcher) {
            val getSearchResultsUseCase = mock<GetSearchResultsUseCase>()

            val screenModel = SearchScreenModel(
                getSearchResultsUseCase = getSearchResultsUseCase
            )

            val expectedState = mockedResults()

            everySuspend { getSearchResultsUseCase("") } returns flowOf(
                ApiResponse.Success(expectedState)
            )

            screenModel.getSearchItems("")

            advanceUntilIdle()

            assertEquals(expectedState, screenModel.state.value.results)
        }
}