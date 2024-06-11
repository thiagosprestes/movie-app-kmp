package di

import com.example.home.di.homeModule
import com.example.movie.di.movieModule
import com.example.network.di.networkModule
import com.example.search.di.searchModule

fun appModule() =
    listOf(
        networkModule,
        homeModule,
        movieModule,
        searchModule
    )
