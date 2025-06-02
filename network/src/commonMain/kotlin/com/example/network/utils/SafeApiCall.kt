package com.example.network.utils

import com.example.core.data.model.ApiResponse
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> safeApiCall(apiCall: suspend () -> T): Flow<ApiResponse<T>> = flow {
    emit(ApiResponse.Loading)
    try {
        val response = apiCall()
        Napier.i("Success on get api data")
        emit(ApiResponse.Success(response))
    } catch (e: Exception) {
        Napier.e("Error on get api data", e)
        emit(ApiResponse.Error(e.message ?: "Unknown error occurred"))
    }
}