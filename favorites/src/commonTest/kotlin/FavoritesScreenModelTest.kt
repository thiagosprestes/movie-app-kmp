package presentation

import com.example.database.model.DatabaseResponse
import com.example.favorites.data.model.FavoriteMovie
import com.example.favorites.domain.usecase.GetFavoritesUseCase
import com.example.favorites.presentation.FavoritesScreenModel
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
class FavoritesScreenModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun mockedFavorites() = listOf(
        FavoriteMovie(
            id = 1,
            title = "Inception",
            posterPath = "/poster1.jpg",
        )
    )

    @Test
    fun `Given a call on favorites use case when init the screen then set the state with a movie`() =
        runTest(testDispatcher) {
            val getFavoritesUseCase = mock<GetFavoritesUseCase>()

            val screenModel = FavoritesScreenModel(
                getFavoritesUseCase = getFavoritesUseCase,
            )

            val expectedState = mockedFavorites()

            everySuspend { getFavoritesUseCase() } returns flowOf(
                DatabaseResponse.Success(expectedState)
            )

            screenModel.getFavorites()

            advanceUntilIdle()

            assertEquals(expectedState, screenModel.state.value.favorites)
        }

    @Test
    fun `Given a call on favorites use case when init the screen then set the state with empty list`() =
        runTest(testDispatcher) {
            val getFavoritesUseCase = mock<GetFavoritesUseCase>()

            val screenModel = FavoritesScreenModel(
                getFavoritesUseCase = getFavoritesUseCase,
            )

            val expectedState: List<FavoriteMovie> = emptyList()

            everySuspend { getFavoritesUseCase() } returns flowOf(
                DatabaseResponse.Success(expectedState)
            )

            screenModel.getFavorites()

            advanceUntilIdle()

            assertEquals(expectedState, screenModel.state.value.favorites)
        }
}