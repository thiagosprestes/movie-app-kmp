package di

import data.api.httpClient
import data.repository.MovieRepository
import data.repository.MovieRepositoryImpl
import org.koin.dsl.module
import ui.screens.Home.HomeScreenModel

val httpClientModule = module {
    single { httpClient }
}

val movieRepositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}

val homeScreenModelModule = module {
    factory { HomeScreenModel(get()) }
}

fun appModule() = listOf(httpClientModule, movieRepositoryModule, homeScreenModelModule)
