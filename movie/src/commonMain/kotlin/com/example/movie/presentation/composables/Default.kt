package com.example.movie.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.data.model.HomeMovie
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.data.model.MovieCast
import com.example.movie.data.model.MovieDetail

@Composable
fun Default(
    movie: MovieDetail,
    cast: List<MovieCast>,
    similar: List<HomeMovie>,
    onGoToMovie: (movieId: Int) -> Unit
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Details(
            movie.backdropPath,
            movie.title,
            movie.genres,
            movie.runtime,
            movie.releaseDate,
            movie.voteAverage,
            movie.voteCount
        )
        Text(
            "Descrição",
            color = primaryWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 12.dp).padding(horizontal = 16.dp)
        )
        Text(
            movie.overview,
            color = primaryWhite.copy(alpha = 0.8F),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 30.dp).padding(horizontal = 16.dp)
        )
        Cast(cast)
        Similar(similar, onGoToMovie)
    }
}
