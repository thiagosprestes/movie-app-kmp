package com.example.movie.presentation.composables.defaultState

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.domain.remote.model.MovieGenre
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun movieScreenGenres(
    title: String,
    genres: List<MovieGenre>,
) {
    movieScreenList(
        title = title,
        list = genres,
    ) {
        Row(
            modifier = Modifier.padding(end = 14.dp)
        ) {
            Text(
                text = it.name,
                color = primaryWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .background(Color(0xFF303243), shape = RoundedCornerShape(34.dp))
                    .padding(horizontal = 18.dp, vertical = 6.dp)
            )
        }
    }
}

@Preview
@Composable
fun MovieScreenGenresPreview() {
    movieScreenGenres(
        title = "Genres",
        genres = listOf(
            MovieGenre(id = 1, name = "Action"),
            MovieGenre(id = 2, name = "Adventure"),
            MovieGenre(id = 3, name = "Comedy"),
            MovieGenre(id = 4, name = "Drama")
        )
    )
}