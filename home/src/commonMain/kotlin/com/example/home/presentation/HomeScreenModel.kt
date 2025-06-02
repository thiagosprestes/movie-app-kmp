package com.example.home.presentation

import cafe.adriel.voyager.core.model.screenModelScope
import com.example.core.presentation.composables.UiStateScreenModel
import com.example.home.domain.useCase.GetHomeUseCase
import com.example.home.presentation.model.HomeScreenActions
import com.example.home.presentation.model.HomeState
import com.example.home.presentation.model.OnInitHomeScreen
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val getHomeUseCase: GetHomeUseCase,
) : UiStateScreenModel<HomeState>() {
    private fun onInit() = screenModelScope.launch {
        collectApiResponse(getHomeUseCase())
    }

    internal fun handleAction(action: HomeScreenActions) = when (action) {
        OnInitHomeScreen -> onInit()
    }
}