package com.example.network.utils

import com.example.network.utils.ApiResponse
import io.github.aakira.napier.Napier

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResponse<T> {
    return try {
        ApiResponse.Loading
        val response = apiCall.invoke()
        Napier.i("Success on get api data")
        ApiResponse.Success(response)
    } catch (e: Exception) {
        Napier.e("Error on get api data", e)
        ApiResponse.Error(e.toString())
    }
}