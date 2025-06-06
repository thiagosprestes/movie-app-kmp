package com.example.movie.presentation.composables.defaultState

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.theme.primaryWhite
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun movieScreenDescription(
    title: String,
    description: String,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            title,
            color = primaryWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            description,
            color = primaryWhite.copy(alpha = 0.8F),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}

@Preview
@Composable
fun movieScreenDescriptionPreview() {
    movieScreenDescription(
        title = "Movie Description",
        description = "This is a sample description of the movie. It provides an overview of the plot and main themes.",
    )
}