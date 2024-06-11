package com.example.home.domain.useCase

import com.example.network.utils.ApiResponse
import com.example.home.data.model.HomeMovie
import com.example.home.domain.repository.HomeRepository
import com.example.network.utils.safeApiCall
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUpcomingUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Flow<ApiResponse<List<HomeMovie>>> = flow {
        val response = safeApiCall {
            repository.getUpcoming()
        }
        Napier.i(
            tag = "GetUpcomingUseCase",
            message = "$response"
        )
        emit(response)
    }
}