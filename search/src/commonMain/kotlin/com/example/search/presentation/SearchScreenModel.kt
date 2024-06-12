package com.example.search.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.core.data.model.ScreenState
import com.example.network.utils.ApiResponse
import com.example.search.domain.useCase.GetSearchResultsUseCase
import com.example.search.presentation.model.SearchState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchScreenModel(
    private val getSearchResultsUseCase: GetSearchResultsUseCase
) : StateScreenModel<SearchState>(SearchState()) {
    suspend fun getSearchItems(query: String) {
        getSearchResultsUseCase(query).collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> {
                    if (result.data.isEmpty()) {
                        mutableState.update {
                            it.copy(
                                state = ScreenState.DEFAULT, isEmptyResult = true
                            )
                        }
                        return@collectLatest
                    }

                    mutableState.update {
                        it.copy(
                            state = ScreenState.DEFAULT,
                            results = result.data,
                            isEmptyResult = false
                        )
                    }
                }

                is ApiResponse.Error -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.ERROR
                        )
                    }
                }

                is ApiResponse.Loading -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.LOADING
                        )
                    }
                }
            }
        }
    }

    fun handleOnRetry(query: String) {
        screenModelScope.launch {
            getSearchItems(query)
        }
    }
}
