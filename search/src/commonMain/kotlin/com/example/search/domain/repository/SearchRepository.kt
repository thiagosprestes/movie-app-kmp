package com.example.search.domain.repository

import com.example.core.data.model.HomeMovie

interface SearchRepository {
    suspend fun getSearchItems(query: String): List<HomeMovie>
}