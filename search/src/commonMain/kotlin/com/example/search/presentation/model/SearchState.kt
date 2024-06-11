package com.example.search.presentation.model

import com.example.core.data.model.HomeMovie
import com.example.core.data.model.ScreenState

data class SearchState(
    val state: ScreenState = ScreenState.DEFAULT,
    val results: List<HomeMovie> = emptyList(),
)