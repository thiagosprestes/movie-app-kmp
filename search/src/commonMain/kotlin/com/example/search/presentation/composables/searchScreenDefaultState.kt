package com.example.search.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core.data.model.HomeMovie
import com.example.core.presentation.composables.FontSize
import com.example.core.presentation.composables.Text
import com.example.core.presentation.theme.primaryWhite

@Composable
fun searchScreenDefaultState(
    movies: List<HomeMovie>,
    hasNoResultsFound: Boolean,
    onGoToMovie: (Long) -> Unit
) {
    Column {
        if (hasNoResultsFound) {
            Text(
                text = "Nenhum resultado encontrado",
                color = primaryWhite,
                fontSize = FontSize.MEDIUM,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        if (movies.isNotEmpty()) {
            Text(
                text = "Resultados",
                color = primaryWhite,
                fontSize = FontSize.MEDIUM,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp
        ) {
            items(movies, key = { it.id }) {
                searchResultListItem(
                    movie = it,
                ) {
                    onGoToMovie(it)
                }
            }
        }
    }
}