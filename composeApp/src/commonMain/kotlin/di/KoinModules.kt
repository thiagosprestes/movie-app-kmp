package di

import data.api.httpClient
import data.repository.MovieRepositoryImpl
import org.koin.dsl.module

val httpModule = module {
    single { httpClient }
}

val movieRepositoryModule = module {
    factory { MovieRepositoryImpl(get()) }
}

fun appModule() = listOf(httpModule, movieRepositoryModule)
