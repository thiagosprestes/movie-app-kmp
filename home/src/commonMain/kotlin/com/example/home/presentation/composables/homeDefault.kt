package com.example.home.presentation.composables

import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.data.model.HomeMovie
import com.example.core.presentation.theme.backgroundGradient
import com.example.core.utils.windowInsetsPadding
import com.example.home.presentation.model.HomeScreenSectionUiModel

@Composable
fun homeDefault(
    nowPlaying: List<HomeMovie>,
    trending: HomeScreenSectionUiModel,
    upcoming: HomeScreenSectionUiModel,
    onGoToMovie: (Long) -> Unit,
) {
    Column(
        Modifier
            .background(backgroundGradient)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 28.dp).windowInsetsPadding(
                WindowInsets.safeDrawing
                    .only(WindowInsetsSides.Top)
                    .asPaddingValues()
                    .calculateTopPadding()
            ),
    ) {
        homeCarousel(movies = nowPlaying, onGoToMovie = onGoToMovie)
        homeMoviesList(sectionUiModel = trending, onGoToMovie = onGoToMovie)
        homeMoviesList(sectionUiModel = upcoming, onGoToMovie = onGoToMovie)
    }
}

