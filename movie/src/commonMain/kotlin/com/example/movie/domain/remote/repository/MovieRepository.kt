package com.example.movie.domain.remote.repository

import com.example.core.data.model.HomeMovie
import com.example.movie.data.remote.model.MovieCast
import com.example.movie.data.remote.model.MovieDetail

interface MovieRepository {
    suspend fun getDetails(id: Int): MovieDetail
    suspend fun getCast(id: Int): List<MovieCast>
    suspend fun getSimilar(id: Int): List<HomeMovie>
}