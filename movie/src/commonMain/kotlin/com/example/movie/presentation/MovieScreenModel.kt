package com.example.movie.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import com.example.core.data.model.ScreenState
import com.example.movie.domain.usecase.GetMovieCastUseCase
import com.example.movie.domain.usecase.GetMovieDetailsUseCase
import com.example.movie.domain.usecase.GetSimilarMoviesUseCase
import com.example.movie.presentation.model.MovieState
import com.example.network.utils.ApiResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update

class MovieScreenModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase
) : StateScreenModel<MovieState>(MovieState()) {
    suspend fun getDetails(id: Int) {
        getMovieDetailsUseCase(id).collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.DEFAULT,
                            details = result.data
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
                            state = ScreenState.DEFAULT,
                            cast = result.data
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
                            state = ScreenState.DEFAULT,
                            similar = result.data
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
}
