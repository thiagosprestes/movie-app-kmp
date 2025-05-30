package com.example.movie.di

import com.example.movie.data.local.repository.FavoritesRepositoryImpl
import com.example.movie.data.remote.datasource.MovieDataSource
import com.example.movie.data.remote.datasource.MovieDataSourceImpl
import com.example.movie.data.remote.repository.MovieRepositoryImpl
import com.example.movie.domain.local.repository.FavoritesRepository
import com.example.movie.domain.local.useCase.AddFavoriteUseCase
import com.example.movie.domain.local.useCase.RemoveFavoriteUseCase
import com.example.movie.domain.remote.repository.MovieRepository
import com.example.movie.domain.remote.useCase.GetMovieUseCase
import com.example.movie.domain.remote.useCase.GetMovieUseCaseImpl
import com.example.movie.presentation.MovieScreenModel
import org.koin.dsl.module

val movieModule = module {
    single<MovieDataSource> { MovieDataSourceImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    single<GetMovieUseCase> {
        GetMovieUseCaseImpl(
            movieRepository = get(),
            favoritesRepository = get(),
        )
    }
    single { AddFavoriteUseCase(get()) }
    single { RemoveFavoriteUseCase(get()) }
    factory {
        MovieScreenModel(
            getMovieUseCase = get(),
            addFavoriteUseCase = get(),
            removeFavoriteUseCase = get(),
        )
    }
}