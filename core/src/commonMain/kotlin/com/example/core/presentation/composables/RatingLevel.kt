package com.example.core.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun RatingLevel(level: Double, starSize: Dp) {
    val numOfStars = 5

    fun renderRating(item: Int): Boolean = item <= (level * numOfStars) / 10

    LazyRow {
        items(numOfStars, key = { it }) {
            Icon(
                Icons.Filled.Star,
                modifier = Modifier.size(starSize),
                tint = if (renderRating(it)) Color.Yellow else Color.Gray,
                contentDescription = null
            )
        }
    }
}