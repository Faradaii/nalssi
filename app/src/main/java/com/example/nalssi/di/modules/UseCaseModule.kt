package com.example.nalssi.di.modules

import com.example.nalssi.domain.usecases.weather.*
import org.koin.dsl.module

val UseCaseModule = module {
    single { FetchAllWeatherUseCase(get()) }
    single { FetchDetailWeatherUseCase(get()) }
    single { SearchWeatherUseCase(get()) }

    single { ToggleFavoriteWeatherUseCase(get()) }
    single { GetAllFavoriteWeatherUseCase(get()) }
}