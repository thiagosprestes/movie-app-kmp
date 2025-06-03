package com.example.search.domain.useCase

import com.example.core.data.model.ApiResponse
import com.example.core.data.model.HomeMovie
import com.example.network.utils.safeApiCall
import com.example.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

interface GetSearchResultsUseCase {
    operator fun invoke(query: String): Flow<ApiResponse<List<HomeMovie>>>
}

class GetSearchResultsUseCaseImpl(
    private val repository: SearchRepository
) : GetSearchResultsUseCase {
    override fun invoke(query: String): Flow<ApiResponse<List<HomeMovie>>> = safeApiCall {
        repository.getSearchItems(query)
    }
}