package com.example.search.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core.presentation.composables.Text
import com.example.core.presentation.theme.darkenRed
import com.example.core.presentation.theme.primaryWhite
import kotlinx.coroutines.delay

@Composable
fun searchScreenHeader(
    onSearchItems: (value: String) -> Unit,
) {
    var textInput by remember { mutableStateOf("") }

    LaunchedEffect(key1 = textInput) {
        if (textInput.isBlank()) return@LaunchedEffect

        delay(2000)

        onSearchItems(textInput)
    }

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
}