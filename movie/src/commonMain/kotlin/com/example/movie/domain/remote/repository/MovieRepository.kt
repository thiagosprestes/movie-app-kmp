package com.example.movie.domain.remote.repository

import com.example.core.data.model.HomeMovie
import com.example.movie.domain.remote.model.MovieCast
import com.example.movie.domain.remote.model.MovieDetail

interface MovieRepository {
    suspend fun getDetails(id: Long): MovieDetail
    suspend fun getCast(id: Long): List<MovieCast>
    suspend fun getSimilar(id: Long): List<HomeMovie>
}