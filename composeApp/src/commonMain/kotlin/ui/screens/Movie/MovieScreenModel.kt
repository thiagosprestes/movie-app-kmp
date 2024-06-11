package ui.screens.Movie

import cafe.adriel.voyager.core.model.StateScreenModel
import com.example.core.data.model.movieCredits.Cast
import com.example.core.data.model.movieDetail.MovieDetailResponse
import com.example.network.utils.ApiResponse
import data.model.Movie.Movie
import data.repository.MovieRepository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieScreenModel(
    private val repository: MovieRepository
) : StateScreenModel<MovieScreenModel.State>(State.Loading) {
    private val _details = MutableStateFlow<MovieDetailResponse?>(null)
    val details = _details.asStateFlow()

    private val _cast = MutableStateFlow<List<Cast>>(emptyList())
    val cast = _cast.asStateFlow()

    private val _similar = MutableStateFlow<List<Movie>>(emptyList())
    val similar = _similar.asStateFlow()

    sealed class State {
        object Loading : State()
        object Error : State()
        object Default : State()
    }

    suspend fun getDetails(id: Int) {
        mutableState.value = State.Loading

        repository.getDetails(id).collect { result ->
            when (result) {
                is ApiResponse.Success -> {
                    _details.value = result.data
                    mutableState.value = State.Default
                }

                is ApiResponse.Error -> mutableState.value = State.Error
                ApiResponse.Loading -> TODO()
            }
        }
    }

    suspend fun getCredits(id: Int) {
        mutableState.value = State.Loading

        repository.getCredits(id).collect { result ->
            when (result) {
                is ApiResponse.Success -> {
                    _cast.value = result.data.cast.take(5)
                    mutableState.value = State.Default
                }

                is ApiResponse.Error -> mutableState.value = State.Error
                ApiResponse.Loading -> TODO()
            }
        }
    }

    suspend fun getSimilar(id: Int) {
        mutableState.value = State.Loading

        repository.getSimilar(id).collect { result ->
            when (result) {
                is ApiResponse.Success -> {
                    _similar.value = result.data.results.take(5)
                    mutableState.value = State.Default
                }

                is ApiResponse.Error -> mutableState.value = State.Error
                ApiResponse.Loading -> TODO()
            }
        }
    }
}
