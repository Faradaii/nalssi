package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.repositories.IWeatherRepository

class FetchDetailWeatherUseCase (private val weatherRepository: IWeatherRepository) {
    suspend fun execute(id: String) {
        weatherRepository.fetchDetailWeather(id)
    }
}