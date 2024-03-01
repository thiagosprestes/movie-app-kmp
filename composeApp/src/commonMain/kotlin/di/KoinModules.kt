package di

import data.api.httpClient
import data.repository.HomeRepository.HomeRepository
import data.repository.HomeRepository.HomeRepositoryImpl
import data.repository.MovieRepository.MovieRepository
import data.repository.MovieRepository.MovieRepositoryImpl
import data.repository.SearchRepository.SearchRepository
import data.repository.SearchRepository.SearchRepositoryImpl
import org.koin.dsl.module
import ui.screens.Home.HomeScreenModel
import ui.screens.Movie.MovieScreenModel
import ui.screens.Movie.SearchScreenModel

val httpClientModule = module {
    single { httpClient }
}

val homeRepositoryModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
}

val homeScreenModelModule = module {
    factory { HomeScreenModel(get()) }
}

val movieRepositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}

val movieScreenModelModule = module {
    factory { MovieScreenModel(get()) }
}

val searchRepositoryModule = module {
    single<SearchRepository> { SearchRepositoryImpl(get()) }
}

val searchScreenModelModule = module {
    factory { SearchScreenModel(get()) }
}

fun appModule() =
    listOf(
        httpClientModule,
        homeRepositoryModule,
        homeScreenModelModule,
        movieRepositoryModule,
        movieScreenModelModule,
        searchRepositoryModule,
        searchScreenModelModule
    )
