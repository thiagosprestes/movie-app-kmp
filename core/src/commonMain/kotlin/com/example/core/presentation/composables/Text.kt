package com.example.core.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.core.presentation.theme.primaryWhite
import androidx.compose.material.Text as ComposeText

enum class FontSize(
    val size: TextUnit
) {
    SMALL(12.sp),
    MEDIUM(14.sp),
    MEDIUM_X(16.sp),
    LARGE(18.sp),
    LARGE_X(22.sp),
    LARGE_XX(38.sp),
}

@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: FontSize = FontSize.MEDIUM,
    color: Color = primaryWhite,
    textAlign: TextAlign = TextAlign.Start,
    lineHeight: TextUnit = TextUnit.Unspecified,
) {
    ComposeText(
        text = text,
        modifier = modifier,
        fontWeight = fontWeight,
        fontSize = fontSize.size,
        color = color,
        textAlign = textAlign,
        lineHeight = lineHeight,
    )
}