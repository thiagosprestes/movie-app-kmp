package ui.screens.Home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

object HomeScreen: Screen {
    @Composable
    override fun Content() {
        Text("Hello world!")
    }
}