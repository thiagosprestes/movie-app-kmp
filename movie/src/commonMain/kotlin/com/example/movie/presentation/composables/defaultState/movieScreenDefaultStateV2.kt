package com.example.movie.presentation.composables.defaultState

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.data.model.HomeMovie
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.domain.remote.model.MovieCast
import com.example.movie.presentation.model.MovieCasting
import com.example.movie.presentation.model.MovieDetails
import com.example.movie.presentation.model.MovieHeader
import com.example.movie.presentation.model.MovieSimilar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun movieScreenDefaultStateV2(
    params: MovieScreenDefaultStateParams
) {
    with(params) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            movieScreenBackdrop(
                title = header.title,
                releaseDate = header.releaseDate,
                runtime = header.runtime,
                percent = 0.75f,
                backdropPath = header.backdropPath,
            )
            Divider(
                color = primaryWhite.copy(alpha = 0.2F),
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
            )
            movieScreenDescription(
                title = details.descriptionTitle,
                description = details.descriptionText,
                buttonText = details.buttonText,
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

@Preview
@Composable
fun MovieScreenPreview() {
    movieScreenDefaultStateV2(
        params = MovieScreenDefaultStateParams(
            header = MovieHeader(
                isFavorite = false,
                starColor = primaryWhite,
                backdropPath = "",
                title = "Batman: the dark knight",
                genres = "Action, Adventure",
                rating = 5.0,
                runtime = "130 min",
                releaseDate = "2008-07-18",
                voteAverage = 5.0,
                voteCount = 1000,
            ),
            details = MovieDetails(
                descriptionTitle = "Description",
                descriptionText = "A thrilling action movie that follows the journey of Batman as he battles against the Joker in Gotham City.",
                buttonText = "Watch Trailer"
            ),
            casting = MovieCasting(
                title = "Cast",
                characters = listOf(
                    MovieCast(id = 1, name = "Christian Bale"),
                    MovieCast(id = 2, name = "Heath Ledger"),
                    MovieCast(id = 3, name = "Aaron Eckhart")
                )
            ),
            similar = MovieSimilar(
                title = "Similar Movies",
                movies = listOf(
                    HomeMovie(
                        id = 1, title = "Batman Begins", backdropPath = "",
                        posterPath = "",
                        voteAverage = 0.0,
                        voteCount = 0,
                    ),
                    HomeMovie(
                        id = 2, title = "The Dark Knight Rises", backdropPath = "",
                        posterPath = "",
                        voteAverage = 0.0,
                        voteCount = 0,
                    ),
                    HomeMovie(
                        id = 3, title = "Batman v Superman: Dawn of Justice", backdropPath = "",
                        posterPath = "",
                        voteAverage = 0.0,
                        voteCount = 0,
                    )
                )
            )
        ) {}
    )
}