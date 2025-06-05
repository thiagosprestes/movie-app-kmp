package com.example.movie.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.presentation.theme.primaryWhite
import com.example.core.utils.windowInsetsPadding
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Star
import compose.icons.fontawesomeicons.solid.ArrowLeft

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
                FontAwesomeIcons.Solid.ArrowLeft,
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
                    FontAwesomeIcons.Regular.Star,
                    modifier = Modifier.size(24.dp),
                    tint = starColor,
                    contentDescription = null,
                )
            }
        }
    }
}