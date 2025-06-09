package com.example.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.data.model.HomeMovie
import com.example.core.presentation.composables.Badge
import com.example.core.presentation.composables.BadgeType
import com.example.core.presentation.theme.backgroundGradient
import com.example.core.utils.windowInsetsPadding
import com.example.home.presentation.model.HomeScreenMovieSectionUiModel
import com.example.home.presentation.model.HomeScreenOptionUiModel
import com.example.home.presentation.model.HomeScreenSelectedOption
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun homeScreenDefaultState(
    nowPlayingMovies: List<HomeMovie>,
    trendingMovies: HomeScreenMovieSectionUiModel,
    upcomingMovies: HomeScreenMovieSectionUiModel,
    options: List<HomeScreenOptionUiModel>,
    selectedOption: HomeScreenSelectedOption,
    onGoToMovie: (Long) -> Unit,
    onSelectOption: (HomeScreenSelectedOption) -> Unit
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
        LazyRow(
            modifier = Modifier.padding(start = 16.dp, bottom = 20.dp)
        ) {
            items(options) {
                Badge(
                    text = it.title,
                    type = BadgeType.LIGHT,
                    showBackground = selectedOption == it.type,
                    onPress = {
                        onSelectOption(it.type)
                    }
                )
            }
        }
        homeCarousel(movies = nowPlayingMovies) {
            onGoToMovie(it)
        }
        homeMoviesList(sectionUiModel = trendingMovies) {
            onGoToMovie(it)
        }
        homeMoviesList(sectionUiModel = upcomingMovies) {
            onGoToMovie(it)
        }
    }
}

@Preview
@Composable
fun HomeScreenDefaultStatePreview() {
    homeScreenDefaultState(
        nowPlayingMovies = emptyList(),
        trendingMovies = HomeScreenMovieSectionUiModel(
            title = "Trending",
            movies = emptyList()
        ),
        upcomingMovies = HomeScreenMovieSectionUiModel(
            title = "Upcoming",
            movies = emptyList()
        ),
        options = listOf(
            HomeScreenOptionUiModel(
                title = "Filmes",
                type = HomeScreenSelectedOption.MOVIES
            ),
            HomeScreenOptionUiModel(
                title = "Séries",
                type = HomeScreenSelectedOption.SHOWS
            ),
        ),
        selectedOption = HomeScreenSelectedOption.MOVIES,
        onGoToMovie = {},
        onSelectOption = {},
    )
}