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

    private fun setMovies(
        moviesUiModel: HomeScreenMoviesUiModel,
        types: List<HomeScreenOptionUiModel>
    ) = mutableState.update {
        it.copy(
            state = ScreenState.DEFAULT,
            moviesUiModel = HomeScreenMoviesUiModel(
                nowPlayingMovies = moviesUiModel.nowPlayingMovies,
                trendingMovies = moviesUiModel.trendingMovies,
                upcomingMovies = moviesUiModel.upcomingMovies,
            ),
            types = types,
        )
    }

    private fun setTvShows(
        tvShowsUiModel: HomeScreenTvShowsUiModel,
        types: List<HomeScreenOptionUiModel>
    ) = mutableState.update {
        it.copy(
            state = ScreenState.DEFAULT,
            tvShowsUiModel = HomeScreenTvShowsUiModel(
                popularTvShows = tvShowsUiModel.popularTvShows,
                topRatedTvShows = tvShowsUiModel.topRatedTvShows,
                onTheAirTvShows = tvShowsUiModel.onTheAirTvShows,
            ),
            types = types,
        )
    }

    private fun setSuccess(data: HomeState) {
        val isMovieSelectedType = data.selectedType == HomeScreenSelectedOption.MOVIES

        when {
            isMovieSelectedType -> setMovies(data.moviesUiModel, data.types)
            else -> setTvShows(data.tvShowsUiModel, data.types)
        }
    }

    private fun onGetMovies() = screenModelScope.launch {
        getHomeUseCase(
            option = mutableState.value.selectedType
        ).collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> setSuccess(data = result.data)
                is ApiResponse.Error -> setError()
                is ApiResponse.Loading -> setLoading()
            }
        }
    }

    private fun onGetTvShows() = screenModelScope.launch {
        getHomeUseCase(
            option = mutableState.value.selectedType
        ).collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> setSuccess(data = result.data)
                is ApiResponse.Error -> setError()
                is ApiResponse.Loading -> setLoading()
            }
        }
    }

    private fun handleOnSelectOption(option: HomeScreenSelectedOption) {
        mutableState.update {
            it.copy(
                selectedType = option
            )
        }
        when (option) {
            HomeScreenSelectedOption.MOVIES -> onGetMovies()
            HomeScreenSelectedOption.SHOWS -> onGetTvShows()
        }
    }

    internal fun handleAction(action: HomeScreenActions) = when (action) {
        is OnInitHomeScreen -> onGetMovies()
        is OnSelectOption -> handleOnSelectOption(action.option)
    }
}