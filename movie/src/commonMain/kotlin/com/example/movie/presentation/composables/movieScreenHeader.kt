package com.example.movie.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.presentation.theme.primaryWhite
import com.example.core.utils.windowInsetsPadding

@Composable
fun movieScreenHeader(
    isDefaultState: Boolean,
    starColor: Color,
    onGoBack: () -> Unit,
    onToggleFavoriteMovie: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp).windowInsetsPadding(
            WindowInsets.safeDrawing
                .only(WindowInsetsSides.Top)
                .asPaddingValues()
                .calculateTopPadding()
        ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = onGoBack
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                modifier = Modifier.size(24.dp),
                tint = primaryWhite,
                contentDescription = null,
            )
        }
        if (isDefaultState) {
            IconButton(
                onClick = onToggleFavoriteMovie
            ) {
                Icon(
                    Icons.Outlined.Star,
                    modifier = Modifier.size(24.dp),
                    tint = starColor,
                    contentDescription = null,
                )
            }
        }
    }
}