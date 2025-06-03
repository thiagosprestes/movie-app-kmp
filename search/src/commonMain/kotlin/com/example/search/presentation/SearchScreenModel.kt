package com.example.search.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.core.data.model.ApiResponse
import com.example.core.data.model.HomeMovie
import com.example.core.data.model.ScreenState
import com.example.search.domain.useCase.GetSearchResultsUseCase
import com.example.search.presentation.model.SearchState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchScreenModel(
    private val getSearchResultsUseCase: GetSearchResultsUseCase
) : StateScreenModel<SearchState>(SearchState()) {
    private fun setIsEmpty() = mutableState.update {
        it.copy(
            state = ScreenState.DEFAULT, isEmptyResult = true
        )
    }

    private fun setResults(results: List<HomeMovie>) = mutableState.update {
        it.copy(
            state = ScreenState.DEFAULT,
            results = results,
            isEmptyResult = false
        )
    }

    private fun setSuccess(result: List<HomeMovie>) = when {
        result.isEmpty() -> setIsEmpty()
        else -> setResults(result)
    }

    private fun setError() = mutableState.update {
        it.copy(
            state = ScreenState.ERROR
        )
    }

    private fun setLoading() = mutableState.update {
        it.copy(
            state = ScreenState.LOADING
        )
    }

    fun getSearchItems(query: String) = screenModelScope.launch {
        getSearchResultsUseCase(query).collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> setSuccess(result.data)
                is ApiResponse.Error -> setError()
                is ApiResponse.Loading -> setLoading()
            }
        }
    }

    fun handleOnRetry(query: String) = screenModelScope.launch {
        getSearchItems(query)
    }
}
