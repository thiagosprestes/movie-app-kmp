package com.example.home.domain.useCase

import com.example.core.data.model.ApiResponse
import com.example.home.domain.mapper.toHomeScreenSectionUiModel
import com.example.home.domain.repository.HomeRepository
import com.example.home.domain.strings.HomeScreenStrings
import com.example.home.presentation.model.*
import com.example.network.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

interface GetHomeUseCase {
    suspend operator fun invoke(
        option: HomeScreenSelectedOption,
    ): Flow<ApiResponse<HomeState>>
}

class GetHomeUseCaseImpl(
    private val repository: HomeRepository
) : GetHomeUseCase {
    private val strings = HomeScreenStrings()

    private fun getTypes() = listOf(
        HomeScreenOptionUiModel(
            title = strings.movies,
            type = HomeScreenSelectedOption.MOVIES,
        ),
        HomeScreenOptionUiModel(
            title = strings.shows,
            type = HomeScreenSelectedOption.SHOWS,
        ),
    )

    private fun onGetMovies(): Flow<ApiResponse<HomeState>> = safeApiCall {
        val nowPlayingResponse = repository.getNowPlaying()
        val trendingResponse = repository.getTrending()
        val upcomingResponse = repository.getUpcoming()

        val types = getTypes()

        HomeState(
            types = types,
            moviesUiModel = HomeScreenMoviesUiModel(
                nowPlayingMovies = nowPlayingResponse,
                trendingMovies = trendingResponse.toHomeScreenSectionUiModel(strings.trendingTitle),
                upcomingMovies = upcomingResponse.toHomeScreenSectionUiModel(strings.upcomingTitle),
            )
        )
    }

    private fun onGetShows(): Flow<ApiResponse<HomeState>> = safeApiCall {
        val nowPlayingResponse = repository.getOnTheAir()
        val trendingResponse = repository.getPopular()
        val upcomingResponse = repository.getTopRated()

        val types = getTypes()

        HomeState(
            types = types,
            tvShowsUiModel = HomeScreenTvShowsUiModel(
                popularTvShows = trendingResponse.toHomeScreenSectionUiModel(strings.popularTitle),
                topRatedTvShows = upcomingResponse.toHomeScreenSectionUiModel(strings.topRatedTitle),
                onTheAirTvShows = nowPlayingResponse.toHomeScreenSectionUiModel(strings.onTheAirTitle),
            )
        )
    }

    override suspend fun invoke(
        option: HomeScreenSelectedOption,
    ) = when (option) {
        HomeScreenSelectedOption.MOVIES -> onGetMovies()
        HomeScreenSelectedOption.SHOWS -> onGetShows()
    }
}