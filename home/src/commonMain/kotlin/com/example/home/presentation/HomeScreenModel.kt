package com.example.home.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.core.data.model.ApiResponse
import com.example.core.data.model.ScreenState
import com.example.home.domain.useCase.GetHomeUseCase
import com.example.home.presentation.model.*
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

    private fun setSuccess(data: HomeState) {
        mutableState.update {
            it.copy(
                state = ScreenState.DEFAULT,
                nowPlayingMovies = data.nowPlayingMovies,
                trendingMovies = data.trendingMovies,
                upcomingMovies = data.upcomingMovies,
                types = data.types,
            )
        }
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

    private fun handleOnSelectOption(option: HomeScreenSelectedOption) = mutableState.update {
        it.copy(
            selectedType = option
        )
    }

    internal fun handleAction(action: HomeScreenActions) = when (action) {
        is OnInitHomeScreen -> onInit()
        is OnSelectOption -> handleOnSelectOption(action.option)
    }
}