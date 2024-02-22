package ui.screens.Home

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.model.Movie
import data.repository.MovieRepository
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val repository: MovieRepository
) : StateScreenModel<HomeScreenModel.State>(State.Default) {
    sealed class State {
        object Default : State()
        object Loading : State()
        object Error : State()
        data class Result(val movies: List<Movie>) : State()
    }

    fun getNowPlaying() {
        screenModelScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result(movies = repository.getNowPlaying().results)
        }
    }
}