package com.example.database.model

sealed class DatabaseResponse<out T> {
    data class Success<T>(val data: T) : DatabaseResponse<T>()
    data class Error<T>(val error: String) : DatabaseResponse<T>()
    data object Loading : DatabaseResponse<Nothing>()
}