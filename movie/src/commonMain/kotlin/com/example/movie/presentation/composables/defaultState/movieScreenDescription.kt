package com.example.movie.presentation.composables.defaultState

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.theme.primaryWhite
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.PlayCircle
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun movieScreenDescription(
    title: String,
    description: String,
    buttonText: String,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            title,
            color = primaryWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            description,
            color = primaryWhite.copy(alpha = 0.8F),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
        )
        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier.fillMaxWidth().padding(vertical = 30.dp),
            shape = RoundedCornerShape(60.dp),
            enabled = true,
            contentPadding = PaddingValues(12.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Icon(
                    FontAwesomeIcons.Regular.PlayCircle,
                    contentDescription = "Play Trailer",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = buttonText,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun movieScreenDescriptionPreview() {
    movieScreenDescription(
        title = "Movie Description",
        description = "This is a sample description of the movie. It provides an overview of the plot and main themes.",
        buttonText = "Watch Trailer",
    )
}