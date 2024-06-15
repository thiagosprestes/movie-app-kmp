package com.example.movie.domain.remote.usecase

import com.example.movie.data.remote.model.MovieDetail
import com.example.movie.domain.remote.repository.MovieRepository
import com.example.network.utils.ApiResponse
import com.example.network.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieDetailsUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int): Flow<ApiResponse<MovieDetail>> = flow {
        val response = safeApiCall {
            repository.getDetails(id)
        }
        emit(response)
    }
}