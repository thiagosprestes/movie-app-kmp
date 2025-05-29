package com.example.home.domain.useCase

import com.example.core.data.model.HomeMovie
import com.example.home.domain.repository.HomeRepository
import com.example.network.utils.ApiResponse
import com.example.network.utils.safeApiCall
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTrendingUseCase(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<ApiResponse<List<HomeMovie>>> = flow {
        val response = safeApiCall {
            repository.getTrending()
        }
        Napier.i(
            tag = "GetTrendingUseCase",
            message = "$response"
        )
        emit(response)
    }
}