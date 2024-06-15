package com.example.movie.di

import com.example.movie.data.local.repository.FavoritesRepositoryImpl
import com.example.movie.data.remote.datasource.MovieDataSource
import com.example.movie.data.remote.datasource.MovieDataSourceImpl
import com.example.movie.data.remote.repository.MovieRepositoryImpl
import com.example.movie.domain.local.repository.FavoritesRepository
import com.example.movie.domain.local.usecase.AddFavoriteUseCase
import com.example.movie.domain.local.usecase.RemoveFavoriteUseCase
import com.example.movie.domain.local.usecase.VerifyFavoriteUseCase
import com.example.movie.domain.remote.repository.MovieRepository
import com.example.movie.domain.remote.usecase.GetMovieCastUseCase
import com.example.movie.domain.remote.usecase.GetMovieDetailsUseCase
import com.example.movie.domain.remote.usecase.GetSimilarMoviesUseCase
import com.example.movie.presentation.MovieScreenModel
import org.koin.dsl.module

val movieModule = module {
    single<MovieDataSource> { MovieDataSourceImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    single { GetMovieDetailsUseCase(get()) }
    single { GetMovieCastUseCase(get()) }
    single { GetSimilarMoviesUseCase(get()) }
    single { VerifyFavoriteUseCase(get()) }
    single { AddFavoriteUseCase(get()) }
    single { RemoveFavoriteUseCase(get()) }
    factory { MovieScreenModel(get(), get(), get(), get(), get(), get()) }
}