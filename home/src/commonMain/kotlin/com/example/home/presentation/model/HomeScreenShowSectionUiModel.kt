package com.example.home.presentation.model

import com.example.core.presentation.model.ShowUiModel

data class HomeScreenShowSectionUiModel(
    val title: String = "",
    val shows: List<ShowUiModel> = emptyList(),
)