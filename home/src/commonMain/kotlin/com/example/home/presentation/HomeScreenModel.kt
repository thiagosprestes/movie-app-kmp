package com.example.home.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.core.data.model.ApiResponse
import com.example.core.data.model.ScreenState
import com.example.home.domain.useCase.GetHomeUseCase
import com.example.home.presentation.model.HomeScreenActions
import com.example.home.presentation.model.HomeState
import com.example.home.presentation.model.OnInitHomeScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val getHomeUseCase: GetHomeUseCase,
) : StateScreenModel<HomeState>(HomeState()) {
    private fun setLoading() = mutableState.update {
        it.copy(
            state = ScreenState.LOADING
        )
    }

    private fun setError() = mutableState.update {
        it.copy(
            state = ScreenState.ERROR
        )
    }

    private fun setSuccess(data: HomeState) = mutableState.update {
        it.copy(
            state = ScreenState.DEFAULT,
            nowPlayingMovies = data.nowPlayingMovies,
            trendingMovies = data.trendingMovies,
            upcomingMovies = data.upcomingMovies
        )
    }

    private fun onInit() = screenModelScope.launch {
        getHomeUseCase().collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> setSuccess(data = result.data)
                is ApiResponse.Error -> setError()
                is ApiResponse.Loading -> setLoading()
            }
        }
    }

    internal fun handleAction(action: HomeScreenActions) = when (action) {
        OnInitHomeScreen -> onInit()
    }
}