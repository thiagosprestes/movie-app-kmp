package com.example.movie.data.repository

import com.example.core.data.model.HomeMovie
import com.example.core.domain.mapper.toMovie
import com.example.movie.data.datasource.MovieDataSource
import com.example.movie.data.model.MovieCast
import com.example.movie.data.model.MovieDetail
import com.example.movie.domain.mapper.toMovieCast
import com.example.movie.domain.mapper.toMovieDetail
import com.example.movie.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val dataSource: MovieDataSource
) : MovieRepository {
    override suspend fun getDetails(id: Int): MovieDetail {
        val response = dataSource.getDetails(id)
        return response.toMovieDetail()
    }

    override suspend fun getCast(id: Int): List<MovieCast> {
        val response = dataSource.getCredits(id)
        return response.cast.take(10).toMovieCast()
    }

    override suspend fun getSimilar(id: Int): List<HomeMovie> {
        val response = dataSource.getSimilar(id)
        return response.results.take(10).toMovie()
    }
}