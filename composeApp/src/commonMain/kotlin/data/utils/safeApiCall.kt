package data.utils

import data.api.ApiResponse
import io.github.aakira.napier.Napier

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResponse<T> {
    return try {
        val response = apiCall.invoke()
        Napier.i("Success on get api data")
        ApiResponse.Success(response)
    } catch (e: Exception) {
        Napier.e("Error on get api data", e)
        ApiResponse.Error(e.toString())
    }
}