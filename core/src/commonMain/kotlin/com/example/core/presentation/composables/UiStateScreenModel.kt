package com.example.core.presentation.composables

import cafe.adriel.voyager.core.model.StateScreenModel
import com.example.core.data.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

abstract class UiStateScreenModel<T>(
    initial: ApiResponse<T> = ApiResponse.Loading
) : StateScreenModel<ApiResponse<T>>(initial) {
    protected open suspend fun collectApiResponse(
        flow: Flow<ApiResponse<T>>
    ) {
        flow.collectLatest { response ->
            mutableState.value = when (response) {
                is ApiResponse.Loading -> ApiResponse.Loading
                is ApiResponse.Success -> ApiResponse.Success(response.data)
                is ApiResponse.Error -> ApiResponse.Error(response.message)
            }
        }
    }
}