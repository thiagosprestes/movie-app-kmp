package com.example.movie.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.core.data.model.ScreenState
import com.example.database.model.DatabaseResponse
import com.example.movie.domain.local.usecase.AddFavoriteUseCase
import com.example.movie.domain.local.usecase.RemoveFavoriteUseCase
import com.example.movie.domain.local.usecase.VerifyFavoriteUseCase
import com.example.movie.domain.remote.usecase.GetMovieCastUseCase
import com.example.movie.domain.remote.usecase.GetMovieDetailsUseCase
import com.example.movie.domain.remote.usecase.GetSimilarMoviesUseCase
import com.example.movie.presentation.model.MovieState
import com.example.network.utils.ApiResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieScreenModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val verifyFavoriteUseCase: VerifyFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : StateScreenModel<MovieState>(MovieState()) {
    suspend fun getDetails(id: Int) {
        getMovieDetailsUseCase(id).collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.DEFAULT, details = result.data
                        )
                    }
                }

                is ApiResponse.Error -> mutableState.update {
                    it.copy(
                        state = ScreenState.ERROR,
                    )
                }

                is ApiResponse.Loading -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.LOADING
                        )
                    }
                }
            }
        }
    }

    suspend fun getCast(id: Int) {
        getMovieCastUseCase(id).collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.DEFAULT, cast = result.data
                        )
                    }
                }

                is ApiResponse.Error -> mutableState.update {
                    it.copy(
                        state = ScreenState.ERROR,
                    )
                }

                is ApiResponse.Loading -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.LOADING
                        )
                    }
                }
            }
        }
    }

    suspend fun getSimilar(id: Int) {
        getSimilarMoviesUseCase(id).collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.DEFAULT, similar = result.data
                        )
                    }
                }

                is ApiResponse.Error -> mutableState.update {
                    it.copy(
                        state = ScreenState.ERROR,
                    )
                }

                is ApiResponse.Loading -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.LOADING
                        )
                    }
                }
            }
        }
    }

    suspend fun verifyFavorite(id: Int) {
        verifyFavoriteUseCase(id).collectLatest { result ->
            when (result) {
                is DatabaseResponse.Success -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.DEFAULT,
                            isFavorite = result.data
                        )
                    }
                }

                is DatabaseResponse.Error -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.ERROR,
                            isFavorite = false
                        )
                    }
                }

                is DatabaseResponse.Loading -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.LOADING
                        )
                    }
                }
            }
        }
    }

    fun toggleFavorite(id: Int, title: String, posterPath: String?) = screenModelScope.launch {
        if (!mutableState.value.isFavorite) {
            addFavoriteUseCase(id, title, posterPath).collectLatest {
                mutableState.update {
                    it.copy(
                        isFavorite = true
                    )
                }
            }
            return@launch
        }

        removeFavoriteUseCase(id).collectLatest {
            mutableState.update {
                it.copy(
                    isFavorite = false
                )
            }
        }
    }
}
