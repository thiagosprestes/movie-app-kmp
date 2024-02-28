package di

import data.api.httpClient
import data.repository.HomeRepository
import data.repository.HomeRepositoryImpl
import org.koin.dsl.module
import ui.screens.Home.HomeScreenModel

val httpClientModule = module {
    single { httpClient }
}

val movieRepositoryModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
}

val homeScreenModelModule = module {
    factory { HomeScreenModel(get()) }
}

fun appModule() = listOf(httpClientModule, movieRepositoryModule, homeScreenModelModule)
