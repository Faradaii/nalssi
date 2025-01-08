package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.repositories.WeatherRepository

class FetchDetailWeatherUseCase (private val weatherRepository: WeatherRepository) {
    suspend fun execute(id: String) {
        weatherRepository.fetchDetailWeather(id)
    }
}