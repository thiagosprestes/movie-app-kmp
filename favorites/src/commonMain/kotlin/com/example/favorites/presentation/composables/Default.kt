package com.example.favorites.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import coil3.compose.AsyncImage
import com.example.core.presentation.theme.backgroundGradient
import com.example.core.presentation.theme.primaryWhite
import com.example.core.utils.windowInsetsPadding
import com.example.favorites.data.model.FavoriteMovie

@Composable
fun Default(
    favorites: List<FavoriteMovie>,
    onNavigate: (Int) -> Unit
) {
    Column(
        Modifier
            .background(backgroundGradient)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 28.dp, horizontal = 16.dp).windowInsetsPadding(
                WindowInsets.safeDrawing
                    .only(WindowInsetsSides.Top)
                    .asPaddingValues()
                    .calculateTopPadding()
            ),
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Favoritos",
            color = primaryWhite,
            fontSize = 18.sp
        )
        Column(
            Modifier.fillMaxSize().weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (favorites.isEmpty()) {
                Text(
                    text = "Você ainda não tem favoritos",
                    color = primaryWhite,
                    fontSize = 16.sp
                )
                return
            }

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalItemSpacing = 10.dp
            ) {
                items(favorites, key = { it.id }) {
                    Box(
                        Modifier
                            .width(120.dp)
                            .height(213.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                onNavigate(it.id)
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
                                .padding(10.dp)
                        ) {
                            Text(
                                it.title,
                                color = primaryWhite,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }

}