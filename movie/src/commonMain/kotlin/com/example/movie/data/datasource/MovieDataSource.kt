package com.example.movie.data.datasource

import com.example.core.data.model.movieCredits.MovieCreditsResponse
import com.example.core.data.model.movieDetail.MovieDetailResponse
import data.model.Movie.MovieResponse

interface MovieDataSource {
    suspend fun getDetails(id: Int): MovieDetailResponse
    suspend fun getCredits(id: Int): MovieCreditsResponse
    suspend fun getSimilar(id: Int): MovieResponse
}