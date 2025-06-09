package com.example.home.di

import com.example.home.data.dataSource.MovieDataSource
import com.example.home.data.dataSource.MovieDataSourceImpl
import com.example.home.data.dataSource.ShowDataSource
import com.example.home.data.dataSource.ShowDataSourceImpl
import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.domain.repository.HomeRepository
import com.example.home.domain.useCase.GetHomeUseCase
import com.example.home.domain.useCase.GetHomeUseCaseImpl
import com.example.home.presentation.HomeScreenModel
import org.koin.dsl.module

val homeModule = module {
    single<MovieDataSource> { MovieDataSourceImpl(get()) }
    single<ShowDataSource> { ShowDataSourceImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(
        movieDataSource = get(),
        showDataSource = get(),
    ) }
    single<GetHomeUseCase> { GetHomeUseCaseImpl(get()) }
    factory { HomeScreenModel(get()) }
}