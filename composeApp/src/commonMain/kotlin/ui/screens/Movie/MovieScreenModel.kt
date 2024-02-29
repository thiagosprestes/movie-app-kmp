package ui.screens.Movie

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.api.ApiResponse
import data.model.Movie.Movie
import data.model.MovieDetail.MovieDetailResponse
import data.repository.HomeRepository.HomeRepository
import data.repository.MovieRepository.MovieRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ui.screens.Home.HomeScreenModel

class MovieScreenModel(
    private val repository: MovieRepository
) : StateScreenModel<MovieScreenModel.State>(State.Loading) {
    private val _details = MutableStateFlow<MovieDetailResponse?>(null)
    val details = _details.asStateFlow()

    sealed class State {
        object Loading : State()
        object Error : State()
        object Default : State()
    }

    suspend fun getNowPlaying(id: Int) {
        screenModelScope.launch {
            mutableState.value = State.Loading

            repository.getDetails(id).collect { result ->
                when (result) {
                    is ApiResponse.Success -> {
                        _details.value = result.data
                        mutableState.value = State.Default
                    }

                    is ApiResponse.Error -> mutableState.value = State.Error
                }
            }
        }
    }
}
