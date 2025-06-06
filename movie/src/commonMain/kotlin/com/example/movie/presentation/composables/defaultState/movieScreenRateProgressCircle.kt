package com.example.movie.presentation.composables.defaultState

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core.presentation.composables.Text
import com.example.core.presentation.theme.darkenGrey
import com.example.core.presentation.theme.primaryWhite
import com.example.core.presentation.theme.secondaryRed
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun movieScreenRateProgressCircle(
    percent: Float,
) {
    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(50.dp)) {
            drawCircle(
                color = darkenGrey,
                style = Stroke(width = 8.dp.toPx())
            )
            drawArc(
                color = secondaryRed,
                startAngle = -90f,
                sweepAngle = percent * 36,
                useCenter = false,
                style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = "${(percent * 10).toInt()}%",
            color = primaryWhite,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun movieScreenRateProgressCirclePreview() {
    movieScreenRateProgressCircle(
        percent = 7.1f,
    )
}