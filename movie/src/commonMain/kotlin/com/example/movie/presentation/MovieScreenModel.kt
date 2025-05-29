package com.example.movie.presentation

import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.core.data.model.ScreenState
import com.example.core.presentation.theme.primaryWhite
import com.example.movie.domain.local.model.FavoriteMovie
import com.example.movie.domain.local.useCase.AddFavoriteUseCase
import com.example.movie.domain.local.useCase.RemoveFavoriteUseCase
import com.example.movie.domain.remote.model.Movie
import com.example.movie.domain.remote.useCase.GetMovieUseCase
import com.example.movie.presentation.model.MovieState
import com.example.network.utils.ApiResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieScreenModel(
    private val getMovieUseCase: GetMovieUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : StateScreenModel<MovieState>(MovieState()) {
    private fun setSuccessState(movie: Movie) = mutableState.update {
        val starColor = when {
            movie.isFavorite -> Color.Yellow
            else -> primaryWhite
        }

        it.copy(
            id = movie.details?.id ?: 0,
            state = ScreenState.DEFAULT,
            details = movie.details,
            cast = movie.cast,
            similar = movie.similar,
            isFavorite = movie.isFavorite,
            starColor = starColor,
        )
    }

    private fun setLoadingState() = mutableState.update {
        it.copy(
            state = ScreenState.LOADING
        )
    }

    private fun setErrorState() = mutableState.update {
        it.copy(
            state = ScreenState.ERROR
        )
    }

    private fun getMovie(id: Long) = screenModelScope.launch {
        getMovieUseCase(id).collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> setSuccessState(result.data)
                is ApiResponse.Error -> setErrorState()
                is ApiResponse.Loading -> setLoadingState()
            }
        }
    }

    private fun handleOnRemoveFavorite() = screenModelScope.launch {
        val movie = mutableState.value

        removeFavoriteUseCase(movie.id).collectLatest {
            mutableState.update {
                it.copy(
                    isFavorite = false
                )
            }
        }
    }

    private fun handleOnAddFavorite() =
        screenModelScope.launch {
            val movie = mutableState.value

            addFavoriteUseCase(
                movie = FavoriteMovie(
                    id = movie.id,
                    title = movie.details?.title.orEmpty(),
                    posterPath = movie.details?.posterPath.orEmpty()
                )
            ).collectLatest {
                mutableState.update {
                    it.copy(
                        isFavorite = true
                    )
                }
            }
        }

    private fun toggleFavorite() = when {
        mutableState.value.isFavorite -> handleOnRemoveFavorite()
        else -> handleOnAddFavorite()
    }

    internal fun handleAction(action: MovieScreenActions) = when (action) {
        is OnInitMovieScreen -> getMovie(action.id)
        is OnToggleFavoriteMovie -> toggleFavorite()
    }
}
