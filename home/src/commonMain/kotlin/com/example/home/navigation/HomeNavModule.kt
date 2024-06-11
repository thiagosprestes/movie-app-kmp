package com.example.home.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.home.presentation.HomeScreen
import com.example.navigation.SharedScreen

val homeNavModule = screenModule {
    register<SharedScreen.Home> { HomeScreen }
}