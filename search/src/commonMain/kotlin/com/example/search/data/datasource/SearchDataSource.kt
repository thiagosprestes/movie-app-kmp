package com.example.search.data.datasource

import com.example.core.data.model.movie.MovieResponse

interface SearchDataSource {
    suspend fun getSearchItems(query: String): MovieResponse
}