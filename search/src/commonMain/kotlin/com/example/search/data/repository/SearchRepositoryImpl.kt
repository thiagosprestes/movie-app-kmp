package com.example.search.data.repository

import com.example.core.data.model.HomeMovie
import com.example.core.domain.mapper.toMovie
import com.example.search.data.datasource.SearchDataSource
import com.example.search.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val dataSource: SearchDataSource
) : SearchRepository {
    override suspend fun getSearchItems(query: String): List<HomeMovie> {
        println("AQUI $query")
        val response = dataSource.getSearchItems(query)
        return response.results.toMovie()
    }
}