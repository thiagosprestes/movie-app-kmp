package com.example.favorites.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.core.data.model.ScreenState
import com.example.database.model.DatabaseResponse
import com.example.favorites.domain.usecase.GetFavoritesUseCase
import com.example.favorites.presentation.model.FavoritesState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesScreenModel(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : StateScreenModel<FavoritesState>(FavoritesState()) {
    fun getFavorites() = screenModelScope.launch {
        getFavoritesUseCase().collectLatest { response ->
            when (response) {
                is DatabaseResponse.Loading -> {
                    mutableState.update {
                        it.copy(state = ScreenState.LOADING)
                    }
                }

                is DatabaseResponse.Success -> {
                    mutableState.update {
                        it.copy(
                            state = ScreenState.DEFAULT,
                            favorites = response.data
                        )
                    }
                }

                is DatabaseResponse.Error -> {
                    mutableState.update {
                        it.copy(state = ScreenState.ERROR)
                    }
                }
            }
        }
    }
}