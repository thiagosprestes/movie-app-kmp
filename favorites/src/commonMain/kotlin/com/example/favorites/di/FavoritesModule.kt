package com.example.favorites.di

import com.example.favorites.data.repository.FavoritesRepositoryImpl
import com.example.favorites.domain.repository.FavoritesRepository
import com.example.favorites.domain.usecase.GetFavoritesUseCase
import com.example.favorites.domain.usecase.GetFavoritesUseCaseImpl
import com.example.favorites.presentation.FavoritesScreenModel
import org.koin.dsl.module

val favoritesModule = module {
    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    single<GetFavoritesUseCase> { GetFavoritesUseCaseImpl(get()) }
    factory { FavoritesScreenModel(get()) }
}