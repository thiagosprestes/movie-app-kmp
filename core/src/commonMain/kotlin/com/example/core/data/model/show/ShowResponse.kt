package com.example.core.data.model.show

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<Show>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)