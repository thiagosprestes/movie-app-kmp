package com.example.movie.presentation.composables.defaultState

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.core.presentation.composables.FontSize
import com.example.core.presentation.composables.Text
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.domain.remote.model.MovieCast

@Composable
fun movieScreenCast(
    title: String,
    cast: List<MovieCast>
) {
    movieScreenList(
        title = title,
        list = cast,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(end = 23.dp)
        ) {
            AsyncImage(
                model = it.profilePath,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(90.dp)
                    .clip(RoundedCornerShape(50.dp)),
                contentDescription = null,
            )
            Text(
                it.name,
                color = primaryWhite,
                fontSize = FontSize.MEDIUM_X,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                it.character,
                color = primaryWhite,
                fontSize = FontSize.SMALL,
                fontWeight = FontWeight.Light,
            )
        }
    }
}