package di

import data.api.httpClient
import data.repository.HomeRepository.HomeRepository
import data.repository.HomeRepository.HomeRepositoryImpl
import data.repository.MovieRepository.MovieRepository
import data.repository.MovieRepository.MovieRepositoryImpl
import org.koin.dsl.module
import ui.screens.Home.HomeScreenModel
import ui.screens.Movie.MovieScreenModel

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

fun appModule() =
    listOf(
        httpClientModule,
        homeRepositoryModule,
        homeScreenModelModule,
        movieRepositoryModule,
        movieScreenModelModule
    )
