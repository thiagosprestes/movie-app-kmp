package com.example.home.domain.useCase

import com.example.core.data.model.HomeMovie
import com.example.home.domain.repository.HomeRepository
import com.example.network.utils.ApiResponse
import com.example.network.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class GetUpcomingUseCase(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<ApiResponse<List<HomeMovie>>> = safeApiCall {
        repository.getUpcoming()
    }
}