import androidx.compose.ui.window.ComposeUIViewController
import navigation.screenRegistry

fun MainViewController() = ComposeUIViewController(configure = {
    screenRegistry()
}) { App() }
