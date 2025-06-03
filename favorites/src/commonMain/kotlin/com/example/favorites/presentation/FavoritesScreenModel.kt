package com.example.favorites.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.core.data.model.ScreenState
import com.example.database.model.DatabaseResponse
import com.example.favorites.data.model.FavoriteMovie
import com.example.favorites.domain.usecase.GetFavoritesUseCase
import com.example.favorites.presentation.model.FavoritesState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesScreenModel(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : StateScreenModel<FavoritesState>(FavoritesState()) {
    private fun setLoading() = mutableState.update {
        it.copy(state = ScreenState.LOADING)
    }

    private fun setError() = mutableState.update {
        it.copy(state = ScreenState.ERROR)
    }

    private fun setSuccess(favorites: List<FavoriteMovie>) = mutableState.update {
        it.copy(state = ScreenState.DEFAULT, favorites = favorites)
    }

    fun getFavorites() = screenModelScope.launch {
        getFavoritesUseCase().collectLatest { response ->
            when (response) {
                is DatabaseResponse.Loading -> setLoading()
                is DatabaseResponse.Success -> setSuccess(response.data)
                is DatabaseResponse.Error -> setError()
            }
        }
    }
}