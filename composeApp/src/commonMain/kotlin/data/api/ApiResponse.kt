package data.api

sealed class ApiResponse<out T> {
    data class Error<T>(val message: String): ApiResponse<T>()
    data class Success<out T>(val data: T): ApiResponse<T>()
}