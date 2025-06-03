package com.example.favorites.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.theme.backgroundGradient
import com.example.core.presentation.theme.primaryWhite
import com.example.core.utils.windowInsetsPadding
import com.example.favorites.presentation.model.FavoritesState

@Composable
private fun emptyList() {
    Text(
        text = "Você ainda não tem favoritos",
        color = primaryWhite,
        fontSize = 16.sp
    )
}

@Composable
fun favoritesScreenContent(
    state: FavoritesState,
    onGoToMovie: (Long) -> Unit
) {
    Column(
        Modifier
            .background(backgroundGradient)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 28.dp, horizontal = 16.dp).windowInsetsPadding(
                WindowInsets.safeDrawing
                    .only(WindowInsetsSides.Top)
                    .asPaddingValues()
                    .calculateTopPadding()
            ),
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = state.pageTitle,
            color = primaryWhite,
            fontSize = 18.sp
        )
        Column(
            Modifier.fillMaxSize().weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                state.favorites.isEmpty() -> emptyList()
                else -> favoritesList(state.favorites, onGoToMovie)
            }
        }
    }

}