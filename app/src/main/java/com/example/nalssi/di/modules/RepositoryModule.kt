package com.example.nalssi.di.modules

import com.example.nalssi.data.datasources.local.LocalDataSource
import com.example.nalssi.data.datasources.remote.RemoteDataSource
import com.example.nalssi.data.repositories.WeatherRepository
import com.example.nalssi.domain.repositories.IWeatherRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }

    single<IWeatherRepository> { WeatherRepository(get(), get()) }
}