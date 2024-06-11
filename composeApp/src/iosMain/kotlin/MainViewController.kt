import androidx.compose.ui.window.ComposeUIViewController
import ui.navigation.screenRegistry

fun MainViewController() = ComposeUIViewController(configure = {
    screenRegistry()
}) { App() }
