package com.example.home.di

import com.example.home.data.dataSource.MovieDataSource
import com.example.home.data.dataSource.MovieDataSourceImpl
import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.domain.repository.HomeRepository
import com.example.home.domain.useCase.GetNowPlayingUseCase
import com.example.home.domain.useCase.GetTrendingUseCase
import com.example.home.domain.useCase.GetUpcomingUseCase
import com.example.home.presentation.HomeScreenModel
import org.koin.dsl.module

val homeModule = module {
    single<MovieDataSource> { MovieDataSourceImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single {
        GetNowPlayingUseCase(get())
    }
    single {
        GetTrendingUseCase(get())
    }
    single {
        GetUpcomingUseCase(get())
    }
    factory { HomeScreenModel(get(), get(), get()) }
}