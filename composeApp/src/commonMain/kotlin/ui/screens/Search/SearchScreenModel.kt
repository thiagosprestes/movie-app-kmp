package ui.screens.Movie

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.api.ApiResponse
import data.model.Movie.Movie
import data.repository.SearchRepository.SearchRepository
import kotlinx.coroutines.launch

class SearchScreenModel(
    private val repository: SearchRepository
) : StateScreenModel<SearchScreenModel.State>(State.Default) {
    sealed class State {
        object Default : State()
        object Loading : State()
        object Error : State()
        data class Result(val movies: List<Movie>) : State()
    }

    suspend fun getSearchItems(query: String) {
        mutableState.value = State.Loading

        repository.getSearchItems(query).collect { result ->
            when (result) {
                is ApiResponse.Success -> {
                    mutableState.value = State.Result(movies = result.data.results)
                }

                is ApiResponse.Error -> mutableState.value = State.Error
            }
        }
    }

    fun handleOnRetry(query: String) {
        screenModelScope.launch {
            getSearchItems(query)
        }
    }
}
