package com.example.nalssi.di.modules

import com.example.nalssi.domain.usecases.weather.*
import org.koin.dsl.module

val useCaseModule = module {
    single { FetchAllWeatherUseCase(get()) }
    single { FetchDetailWeatherUseCase(get()) }
    single { SearchWeatherUseCase(get()) }

    single { InsertFavoriteWeatherUseCase(get()) }
    single { DeleteFavoriteWeatherUseCase(get()) }
    single { GetAllFavoriteWeatherUseCase(get()) }
}