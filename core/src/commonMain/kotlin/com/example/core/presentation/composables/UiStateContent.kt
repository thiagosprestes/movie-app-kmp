package com.example.core.presentation.composables

import androidx.compose.runtime.Composable
import com.example.core.data.model.ApiResponse

class UiStateContent<T>(
    private val uiState: ApiResponse<T>,
    private val onRetry: () -> Unit = {},
) {
    @Composable
    fun render(
        loading: @Composable () -> Unit = { Loading() },
        error: @Composable (String?, () -> Unit) -> Unit = { msg, retry ->
            Error(
                onRetry = retry
            )
        },
        onSuccess: @Composable (T) -> Unit,
    ) {
        when (uiState) {
            is ApiResponse.Loading -> loading()
            is ApiResponse.Error -> error(uiState.message, onRetry)
            is ApiResponse.Success -> onSuccess(uiState.data)
        }
    }
}