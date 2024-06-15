package com.example.movie.data.remote.repository

import com.example.core.data.model.HomeMovie
import com.example.core.domain.mapper.toMovie
import com.example.movie.data.remote.datasource.MovieDataSource
import com.example.movie.data.remote.model.MovieCast
import com.example.movie.data.remote.model.MovieDetail
import com.example.movie.domain.remote.mapper.toMovieCast
import com.example.movie.domain.remote.mapper.toMovieDetail
import com.example.movie.domain.remote.repository.MovieRepository

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