package com.example.movie.domain.remote.usecase

import com.example.core.data.model.HomeMovie
import com.example.movie.domain.remote.repository.MovieRepository
import com.example.network.utils.ApiResponse
import com.example.network.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSimilarMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int): Flow<ApiResponse<List<HomeMovie>>> = flow {
        val response = safeApiCall {
            repository.getSimilar(id)
        }
        emit(response)
    }
}