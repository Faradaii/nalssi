package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.repositories.IWeatherRepository

class SearchWeatherUseCase (private val weatherRepository: IWeatherRepository) {
    suspend fun execute(query: String) {
        weatherRepository.searchWeather(query)
    }
}