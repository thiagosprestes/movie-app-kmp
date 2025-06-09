package com.example.home.presentation.model

sealed class HomeScreenActions

internal object OnInitHomeScreen: HomeScreenActions()
data class OnSelectOption(val option: HomeScreenSelectedOption): HomeScreenActions()