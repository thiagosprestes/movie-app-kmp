package com.example.movie.di

import com.example.movie.data.datasource.MovieDataSource
import com.example.movie.data.datasource.MovieDataSourceImpl
import com.example.movie.data.repository.MovieRepositoryImpl
import com.example.movie.domain.repository.MovieRepository
import com.example.movie.domain.usecase.GetMovieCastUseCase
import com.example.movie.domain.usecase.GetMovieDetailsUseCase
import com.example.movie.domain.usecase.GetSimilarMoviesUseCase
import com.example.movie.presentation.MovieScreenModel
import org.koin.dsl.module

val movieModule = module {
    single<MovieDataSource> { MovieDataSourceImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single { GetMovieDetailsUseCase(get()) }
    single { GetMovieCastUseCase(get()) }
    single { GetSimilarMoviesUseCase(get()) }
    factory { MovieScreenModel(get(), get(), get()) }
}