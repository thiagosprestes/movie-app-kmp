package com.example.home.domain.useCase

import com.example.core.data.model.ApiResponse
import com.example.home.domain.mapper.toHomeScreenSectionUiModel
import com.example.home.domain.repository.HomeRepository
import com.example.home.domain.strings.HomeScreenStrings
import com.example.home.presentation.model.HomeState
import com.example.network.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

interface GetHomeUseCase {
    suspend operator fun invoke(): Flow<ApiResponse<HomeState>>
}

class GetHomeUseCaseImpl(
    private val repository: HomeRepository
) : GetHomeUseCase {
    private val strings = HomeScreenStrings()

    override suspend fun invoke(): Flow<ApiResponse<HomeState>> = safeApiCall {
        val nowPlayingResponse = repository.getNowPlaying()
        val trendingResponse = repository.getTrending()
        val upcomingResponse = repository.getUpcoming()

        HomeState(
            nowPlayingMovies = nowPlayingResponse,
            trendingMovies = trendingResponse.toHomeScreenSectionUiModel(strings.trendingTitle),
            upcomingMovies = upcomingResponse.toHomeScreenSectionUiModel(strings.upcomingTitle),
        )
    }
}