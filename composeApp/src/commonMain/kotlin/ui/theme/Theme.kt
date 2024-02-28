package ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val colors = darkColors(
    secondary = primaryRed,
    secondaryVariant = secondaryRed,
    onSecondary = darkenRed,
    background = background,
    surface = primaryWhite
)

@Composable
fun AppTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(content = content, colors = colors)
}