package com.example.core.presentation.model

data class ShowUiModel(
    val id: Int,
    val name: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val voteCount: Int
)
