package com.example.core.data.model.upcoming

import com.example.core.data.model.movie.Dates
import com.example.core.data.model.movie.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpcomingResponse(
    @SerialName("dates")
    val dates: Dates,
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<Movie>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)