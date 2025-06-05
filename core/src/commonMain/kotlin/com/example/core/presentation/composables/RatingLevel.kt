package com.example.core.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Star

const val NUM_OF_STARS = 5

@Composable
fun RatingLevel(level: Double, starSize: Dp) {
    fun hasFilledStar(item: Int): Boolean = item <= (level * NUM_OF_STARS) / 10

    fun tint(rate: Int): Color = when {
        hasFilledStar(rate) -> Color.Yellow
        else -> Color.Gray
    }

    LazyRow {
        items(NUM_OF_STARS, key = { it }) {
            Icon(
                FontAwesomeIcons.Regular.Star,
                modifier = Modifier.size(starSize),
                tint = tint(it),
                contentDescription = null
            )
        }
    }
}