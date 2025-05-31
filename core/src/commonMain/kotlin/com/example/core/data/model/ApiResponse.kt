package com.example.core.data.model

sealed class ApiResponse<out T> {
    data object Loading: ApiResponse<Nothing>()
    data class Error<T>(val message: String): ApiResponse<T>()
    data class Success<out T>(val data: T): ApiResponse<T>()
}