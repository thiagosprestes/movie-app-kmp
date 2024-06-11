package com.example.search.data.datasource

import data.model.Movie.MovieResponse

interface SearchDataSource {
    suspend fun getSearchItems(query: String): MovieResponse
}