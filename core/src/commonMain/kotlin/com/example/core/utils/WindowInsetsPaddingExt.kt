package com.example.core.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Modifier.windowInsetsPadding(insets: Dp): Modifier {
    return this.padding(top = insets)
}