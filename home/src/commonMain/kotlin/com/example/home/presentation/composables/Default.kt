package com.example.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.data.model.HomeMovie
import com.example.core.presentation.theme.backgroundGradient

@Composable
fun Default(
    nowPlaying: List<HomeMovie>,
    trending: List<HomeMovie>,
    upcoming: List<HomeMovie>
) {
    Column(
        Modifier
            .background(backgroundGradient)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 28.dp)
    ) {
        Carousel(nowPlaying)
        MoviesList(title = "Em alta", movies = trending)
        MoviesList(title = "Novos lançamentos", movies = upcoming)
    }
}
