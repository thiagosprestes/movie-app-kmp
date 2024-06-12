package com.example.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.example.core.data.model.ScreenState
import com.example.core.presentation.composables.Error
import com.example.core.presentation.composables.Loading
import com.example.core.presentation.theme.backgroundGradient
import com.example.core.presentation.theme.darkenRed
import com.example.core.presentation.theme.primaryWhite
import com.example.search.presentation.composables.Default
import kotlinx.coroutines.delay

object SearchScreen : Screen {
    @Composable
    override fun Content() {
        var textInput by remember { mutableStateOf("") }

        val screenModel = koinScreenModel<SearchScreenModel>()
        val state by screenModel.state.collectAsState()

        LaunchedEffect(key1 = textInput) {
            if (textInput.isBlank()) return@LaunchedEffect

            delay(2000)

            screenModel.getSearchItems(textInput)
        }

        Column(
            Modifier
                .background(
                    backgroundGradient
                )
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row {
                OutlinedTextField(
                    value = textInput,
                    onValueChange = { textInput = it },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(
                            "Buscar...",
                            fontWeight = FontWeight.SemiBold,
                            color = primaryWhite
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = primaryWhite.copy(0.3F),
                        focusedBorderColor = darkenRed,
                        focusedLabelColor = primaryWhite,
                        cursorColor = primaryWhite,
                        textColor = primaryWhite
                    )
                )
            }
            when (state.state) {
                ScreenState.LOADING -> Loading()
                ScreenState.ERROR -> Error(onRetry = {
                    screenModel.handleOnRetry(
                        textInput
                    )
                })

                ScreenState.DEFAULT -> Default(
                    movies = state.results,
                    hasNoResultsFound = state.isEmptyResult
                )
            }
        }
    }
}

