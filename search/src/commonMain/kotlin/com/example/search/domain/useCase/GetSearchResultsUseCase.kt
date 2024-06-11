package com.example.search.domain.useCase

import com.example.core.data.model.HomeMovie
import com.example.network.utils.ApiResponse
import com.example.network.utils.safeApiCall
import com.example.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSearchResultsUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String): Flow<ApiResponse<List<HomeMovie>>> = flow {
        val response = safeApiCall {
            repository.getSearchItems(query)
        }
        println("AQUI2 $response")
        emit(response)
    }
}