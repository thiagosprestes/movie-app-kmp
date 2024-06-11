package com.example.search.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.navigation.SharedScreen
import com.example.search.presentation.SearchScreen

val searchNavModule = screenModule {
    register<SharedScreen.Search> { SearchScreen }
}