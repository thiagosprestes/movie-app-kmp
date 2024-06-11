package com.example.movie.domain.usecase

import com.example.movie.data.model.MovieCast
import com.example.movie.domain.repository.MovieRepository
import com.example.network.utils.ApiResponse
import com.example.network.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieCastUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int): Flow<ApiResponse<List<MovieCast>>> = flow {
        val response = safeApiCall {
            repository.getCast(id)
        }
        emit(response)
    }
}