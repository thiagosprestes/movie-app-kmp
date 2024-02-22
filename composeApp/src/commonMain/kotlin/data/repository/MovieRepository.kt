package data.repository

import data.model.MovieResponse

interface MovieRepository {
    suspend fun getNowPlaying(): MovieResponse
}