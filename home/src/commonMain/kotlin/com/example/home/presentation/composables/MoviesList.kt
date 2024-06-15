package com.example.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.example.core.data.model.HomeMovie
import com.example.core.presentation.composables.RatingLevel
import com.example.core.presentation.theme.primaryWhite
import com.example.navigation.SharedScreen
import com.example.navigation.utils.getScreenRegistry

@Composable
fun MoviesList(
    title: String,
    movies: List<HomeMovie>
) {
    val navigator = LocalNavigator.currentOrThrow

    Column {
        Text(
            title,
            color = primaryWhite,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 16.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(
                start = 16.dp, end = 6.dp
            )
        ) {
            items(movies, key = { it.id }) {
                Box(
                    Modifier
                        .width(150.dp)
                        .height(243.dp)
                        .padding(end = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            navigator.push(getScreenRegistry(SharedScreen.Movie(it.id)))
                        }
                ) {
                    AsyncImage(
                        model = it.posterPath,
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                    )
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    0F to Color.Transparent,
                                    .5F to Color.Black.copy(alpha = 0.5F),
                                    1F to Color.Black.copy(alpha = 0.8F)
                                )
                            )
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(horizontal = 10.dp, vertical = 13.dp)
                    ) {
                        Column {
                            Text(
                                it.title,
                                color = primaryWhite,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RatingLevel(it.voteAverage, 10.dp)
                                Text(
                                    "(${it.voteCount})",
                                    color = primaryWhite,
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}