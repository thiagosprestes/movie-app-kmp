package ui.screens.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.network.utils.PATH_BASE_URL
import data.model.Movie.Movie
import io.github.aakira.napier.Napier
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.delay
import ui.components.Error
import ui.components.Loading
import ui.screens.Movie.MovieScreen
import ui.screens.Movie.SearchScreenModel
import ui.theme.backgroundGradient
import ui.theme.darkenRed
import ui.theme.primaryWhite

object SearchScreen : Screen {
    private val LOG_TAG = "SearchScreen"

    @Composable
    override fun Content() {
        var textInput by remember { mutableStateOf("") }

        val screenModel = getScreenModel<SearchScreenModel>()
        val state by screenModel.state.collectAsState()

        LaunchedEffect(key1 = textInput) {
            if (textInput.isBlank()) return@LaunchedEffect

            delay(2000)

            screenModel.getSearchItems(textInput)
        }

        Column(
            Modifier.background(
                backgroundGradient
            ).fillMaxSize().padding(16.dp)
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
            when (state) {
                is SearchScreenModel.State.Loading -> Loading()
                is SearchScreenModel.State.Error -> Error(onRetry = {
                    screenModel.handleOnRetry(
                        textInput
                    )
                })

                is SearchScreenModel.State.Default -> null
                is SearchScreenModel.State.Result -> SearchResults(
                    (state as SearchScreenModel.State.Result).movies
                )

                else -> {}
            }
        }
    }

    @Composable
    fun SearchResults(movies: List<Movie>) {
        val navigator = LocalNavigator.currentOrThrow

        Column {
            Text(
                "Resultados",
                color = primaryWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalItemSpacing = 10.dp
            ) {
                items(movies, key = { it.id }) {
                    Box(
                        Modifier
                            .width(120.dp)
                            .height(213.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                Napier.i(
                                    tag = LOG_TAG,
                                    message = "press search item with id: ${it.id} title: ${it.title}"
                                )
                                navigator.push(MovieScreen(it.id))
                            }
                    ) {
                        KamelImage(
                            resource = asyncPainterResource("$PATH_BASE_URL${it.posterPath}"),
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
                                it.title!!,
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

