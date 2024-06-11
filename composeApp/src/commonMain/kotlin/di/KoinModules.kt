package di

import com.example.home.di.homeModule
import com.example.movie.di.movieModule
import com.example.network.di.networkModule
import data.repository.SearchRepository.SearchRepository
import data.repository.SearchRepository.SearchRepositoryImpl
import org.koin.dsl.module
import ui.screens.Movie.SearchScreenModel

val searchRepositoryModule = module {
    single<SearchRepository> { SearchRepositoryImpl(get()) }
}

val searchScreenModelModule = module {
    factory { SearchScreenModel(get()) }
}

fun appModule() =
    listOf(
        networkModule,
        homeModule,
        movieModule,
        searchRepositoryModule,
        searchScreenModelModule
    )
