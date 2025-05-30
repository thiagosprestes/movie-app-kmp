package com.example.movie.data.remote.datasource

import com.example.core.data.model.movie.MovieResponse
import com.example.core.data.model.movieCredits.MovieCreditsResponse
import com.example.core.data.model.movieDetail.MovieDetailResponse

interface MovieDataSource {
    suspend fun getDetails(id: Long): MovieDetailResponse
    suspend fun getCredits(id: Long): MovieCreditsResponse
    suspend fun getSimilar(id: Long): MovieResponse
}