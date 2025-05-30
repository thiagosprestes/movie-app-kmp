package com.example.home.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.core.data.model.ScreenState
import com.example.home.domain.useCase.GetNowPlayingUseCase
import com.example.home.domain.useCase.GetTrendingUseCase
import com.example.home.domain.useCase.GetUpcomingUseCase
import com.example.home.presentation.model.HomeState
import com.example.network.utils.ApiResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getTrendingUseCase: GetTrendingUseCase,
    private val getUpcomingUseCase: GetUpcomingUseCase,
) : StateScreenModel<HomeState>(HomeState()) {
    init {
        onInit()
    }

    private suspend fun getNowPlaying() {
        getNowPlayingUseCase().collectLatest { response ->
            when (response) {
                is ApiResponse.Loading -> {
                    mutableState.update {
                        it.copy(state = ScreenState.LOADING)
                    }
                }

                is ApiResponse.Success -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.DEFAULT,
                            nowPlayingMovies = response.data
                        )
                    }
                }

                is ApiResponse.Error -> {
                    mutableState.update {
                        it.copy(state = ScreenState.ERROR)
                    }
                }
            }
        }
    }

    private suspend fun getTrending() {
        getTrendingUseCase().collectLatest { response ->
            when (response) {
                is ApiResponse.Loading -> {
                    mutableState.update {
                        it.copy(state = ScreenState.LOADING)
                    }
                }

                is ApiResponse.Success -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.DEFAULT,
                            trendingMovies = response.data
                        )
                    }
                }

                is ApiResponse.Error -> {
                    mutableState.update {
                        it.copy(state = ScreenState.ERROR)
                    }
                }
            }
        }
    }

    private suspend fun getUpcoming() {
        getUpcomingUseCase().collectLatest { response ->
            when (response) {
                is ApiResponse.Loading -> {
                    mutableState.update {
                        it.copy(state = ScreenState.LOADING)
                    }
                }

                is ApiResponse.Success -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.DEFAULT,
                            upcomingMovies = response.data
                        )
                    }
                }

                is ApiResponse.Error -> {
                    mutableState.update {
                        it.copy(state = ScreenState.ERROR)
                    }
                }
            }
        }
    }

    fun onInit() {
        screenModelScope.launch {
            getNowPlaying()
            getTrending()
            getUpcoming()
        }
    }
}