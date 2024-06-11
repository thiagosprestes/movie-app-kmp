package com.example.network.di

import com.example.network.service.httpClient
import org.koin.dsl.module

val networkModule = module {
    single { httpClient }
}