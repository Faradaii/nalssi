package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.repositories.WeatherRepository

class SearchWeatherUseCase (private val weatherRepository: WeatherRepository) {
    suspend fun execute(query: String) {
        weatherRepository.searchWeather(query)
    }
}