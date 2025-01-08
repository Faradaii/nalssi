package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.repositories.WeatherRepository

class FetchAllWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(){
        weatherRepository.fetchAllWeather()
    }
}