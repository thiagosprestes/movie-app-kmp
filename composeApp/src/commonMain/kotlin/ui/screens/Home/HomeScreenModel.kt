package ui.screens.Home

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.api.ApiResponse
import data.model.Movie.Movie
import data.model.Trending.TrendingItem
import data.repository.HomeRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val repository: HomeRepository
) : StateScreenModel<HomeScreenModel.State>(State.Loading) {
    private val _nowPlaying = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlaying = _nowPlaying.asStateFlow()

    private val _trending = MutableStateFlow<List<Movie>>(emptyList())
    val trending = _trending.asStateFlow()

    private val _upcoming = MutableStateFlow<List<Movie>>(emptyList())
    val upcoming = _upcoming.asStateFlow()

    sealed class State {
        object Loading : State()
        object Error : State()
        object Default : State()
    }

    init {
        screenModelScope.launch {
            getNowPlaying()
            getTrending()
            getUpcoming()
        }
    }

    private suspend fun getNowPlaying() {
        mutableState.value = State.Loading

        repository.getNowPlaying().collect { result ->
            when (result) {
                is ApiResponse.Success -> {
                    _nowPlaying.value = result.data.results.take(5)
                    mutableState.value = State.Default
                }

                is ApiResponse.Error -> mutableState.value = State.Error
            }
        }
    }

    private suspend fun getTrending() {
        mutableState.value = State.Loading

        repository.getTrending().collect { result ->
            when (result) {
                is ApiResponse.Success -> {
                    _trending.value = result.data.results.take(5)
                    mutableState.value = State.Default
                }

                is ApiResponse.Error -> mutableState.value = State.Error
            }
        }
    }

    private fun getUpcoming() {
        mutableState.value = State.Loading

        screenModelScope.launch {
            repository.getUpcoming().collect { result ->
                when (result) {
                    is ApiResponse.Success -> {
                        _upcoming.value = result.data.results.take(5)
                        mutableState.value = State.Default
                    }

                    is ApiResponse.Error -> mutableState.value = State.Error
                }
            }
        }
    }

    fun handleOnRetry() {
        Napier.i("onRetry")
        screenModelScope.launch {
            getNowPlaying()
            getTrending()
            getUpcoming()
        }
    }
}