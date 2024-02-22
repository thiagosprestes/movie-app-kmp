package ui.screens.Home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel

object HomeScreen: Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<HomeScreenModel>()
        val state by screenModel.state.collectAsState()

        when(state) {
            is HomeScreenModel.State.Default -> Text("Default")
            is HomeScreenModel.State.Loading -> Text("Loading")
            is HomeScreenModel.State.Error -> Text("Error")
            is HomeScreenModel.State.Result -> Text("Result")
        }

        LaunchedEffect(currentCompositeKeyHash) {
            screenModel.getNowPlaying()
        }
    }
}