package com.example.search.di

import com.example.search.data.datasource.SearchDataSource
import com.example.search.data.datasource.SearchDataSourceImpl
import com.example.search.data.repository.SearchRepositoryImpl
import com.example.search.domain.repository.SearchRepository
import com.example.search.domain.useCase.GetSearchResultsUseCase
import com.example.search.domain.useCase.GetSearchResultsUseCaseImpl
import com.example.search.presentation.SearchScreenModel
import org.koin.dsl.module

val searchModule = module {
    single<SearchDataSource> { SearchDataSourceImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single<GetSearchResultsUseCase> { GetSearchResultsUseCaseImpl(get()) }
    factory { SearchScreenModel(get()) }
}