package com.example.core.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core.presentation.theme.darkenGrey
import com.example.core.presentation.theme.primaryWhite
import com.example.core.presentation.theme.secondaryRed
import com.example.core.presentation.theme.transparent

enum class BadgeType {
    LIGHT,
    DARK,
}

@Composable
fun Badge(
    text: String,
    type: BadgeType = BadgeType.LIGHT,
    showBackground: Boolean = true,
    modifier: Modifier = Modifier,
    onPress: () -> Unit = { }
) {
    val backgroundColor = when (type) {
        BadgeType.DARK -> darkenGrey
        BadgeType.LIGHT -> secondaryRed
    }

    val color = when {
        showBackground -> backgroundColor
        else -> transparent
    }

    TextButton(
        onClick = onPress,
        shape = RoundedCornerShape(34.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = primaryWhite
        ),
        contentPadding= PaddingValues(
            horizontal = 18.dp,
            vertical = 6.dp
        ),
    ) {
        Text(
            text = text,
            color = primaryWhite,
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSize.SMALL,
        )
    }
}