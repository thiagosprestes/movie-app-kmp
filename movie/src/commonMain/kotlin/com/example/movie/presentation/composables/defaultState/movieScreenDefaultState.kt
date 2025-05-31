package com.example.movie.presentation.composables.defaultState

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
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.presentation.model.MovieCasting
import com.example.movie.presentation.model.MovieDetails
import com.example.movie.presentation.model.MovieHeader
import com.example.movie.presentation.model.MovieSimilar

data class MovieScreenDefaultStateParams(
    val header: MovieHeader,
    val details: MovieDetails,
    val casting: MovieCasting,
    val similar: MovieSimilar,
    val onGoToMovie: (movieId: Long) -> Unit
)

@Composable
fun movieScreenDefaultState(
    params: MovieScreenDefaultStateParams
) {
    with(params) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            with(header) {
                movieScreenDetails(
                    params = MovieScreenDetailsParams(
                        backdropPath = backdropPath,
                        title = title,
                        genres = genres,
                        runtime = runtime,
                        releaseDate = releaseDate,
                        voteAverage = voteAverage,
                        voteCount = voteCount,
                    )
                )
            }
            Text(
                details.descriptionTitle,
                color = primaryWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 12.dp).padding(horizontal = 16.dp)
            )
            Text(
                details.descriptionText,
                color = primaryWhite.copy(alpha = 0.8F),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(bottom = 30.dp).padding(horizontal = 16.dp)
            )
            movieScreenCast(
                title = casting.title,
                cast = casting.characters
            )
            movieScreenSimilar(
                title = similar.title,
                similarMovies = similar.movies,
                onNavigateToMovie = onGoToMovie
            )
        }
    }
}