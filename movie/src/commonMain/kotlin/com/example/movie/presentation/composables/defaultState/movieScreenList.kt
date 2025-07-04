package com.example.movie.presentation.composables.defaultState

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core.presentation.composables.FontSize
import com.example.core.presentation.composables.Text
import com.example.core.presentation.theme.primaryWhite

@Composable
fun <T>movieScreenList(
    title: String,
    list: List<T>,
    itemContent: @Composable (T) -> Unit
) {
    Text(
        title,
        color = primaryWhite,
        fontSize = FontSize.LARGE,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
    )
    LazyRow(contentPadding = PaddingValues(start = 16.dp, bottom = 30.dp)) {
        items(list) {
            itemContent(it)
        }
    }
}