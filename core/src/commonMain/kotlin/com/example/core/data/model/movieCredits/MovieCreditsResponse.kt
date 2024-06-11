package com.example.core.data.model.movieCredits


import com.example.core.data.model.movieCredits.Cast
import com.example.core.data.model.movieCredits.Crew
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditsResponse(
    @SerialName("cast")
    val cast: List<Cast>,
    @SerialName("crew")
    val crew: List<Crew>,
    @SerialName("id")
    val id: Int
)