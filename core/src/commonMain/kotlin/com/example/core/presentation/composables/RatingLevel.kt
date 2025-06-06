package com.example.core.presentation.composables

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Star

const val NUM_OF_STARS = 5

@Composable
fun RatingLevel(level: Double, starSize: Int) {
    fun hasFilledStar(item: Int): Boolean = item <= (level * NUM_OF_STARS) / 10

    fun tint(rate: Int): Color = when {
        hasFilledStar(rate) -> Color.Yellow
        else -> Color.Gray
    }

    LazyRow {
        items(NUM_OF_STARS, key = { it }) {
            Icon(
                icon = FontAwesomeIcons.Solid.Star,
                color = tint(it),
                size = starSize
            )
        }
    }
}