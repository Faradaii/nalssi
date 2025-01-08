package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.repositories.IWeatherRepository

class FetchDetailWeatherUseCase (private val IWeatherRepository: IWeatherRepository) {
    suspend fun execute(id: String) {
        IWeatherRepository.fetchDetailWeather(id)
    }
}