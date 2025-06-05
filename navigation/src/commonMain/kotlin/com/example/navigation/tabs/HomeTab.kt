package com.example.navigation.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.navigation.SharedScreen
import com.example.navigation.utils.getScreenRegistry
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Home

object HomeTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Início"
            val icon = rememberVectorPainter(FontAwesomeIcons.Solid.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(getScreenRegistry(SharedScreen.Home))
    }
}