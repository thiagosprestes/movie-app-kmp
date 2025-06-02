package com.example.home.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.core.data.model.ScreenState
import com.example.home.domain.useCase.GetHomeUseCase
import com.example.home.presentation.model.HomeScreenActions
import com.example.home.presentation.model.HomeState
import com.example.home.presentation.model.OnInitHomeScreen
import com.example.network.utils.ApiResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val getHomeUseCase: GetHomeUseCase,
) : StateScreenModel<HomeState>(HomeState()) {
    private fun setState(
        state: ScreenState,
        data: HomeState = HomeState()
    ) = mutableState.update {
        it.copy(
            state = state,
            nowPlayingMovies = data.nowPlayingMovies,
            trendingMovies = data.trendingMovies,
            upcomingMovies = data.upcomingMovies
        )
    }

    private fun onInit() = screenModelScope.launch {
        getHomeUseCase().collectLatest { response ->
            when (response) {
                is ApiResponse.Loading -> setState(ScreenState.LOADING)
                is ApiResponse.Success -> setState(ScreenState.DEFAULT, response.data)
                is ApiResponse.Error -> setState(ScreenState.ERROR)
            }
        }
    }

    internal fun handleAction(action: HomeScreenActions) = when (action) {
        OnInitHomeScreen -> onInit()
    }
}