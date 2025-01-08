package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.repositories.IWeatherRepository

class SearchWeatherUseCase (private val IWeatherRepository: IWeatherRepository) {
    suspend fun execute(query: String) {
        IWeatherRepository.searchWeather(query)
    }
}