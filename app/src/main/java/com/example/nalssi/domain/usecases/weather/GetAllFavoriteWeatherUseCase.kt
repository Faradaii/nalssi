package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.repositories.IWeatherRepository

class GetAllFavoriteWeatherUseCase (private val weatherRepository: IWeatherRepository) {
    suspend fun execute() {
        weatherRepository.getAllFavoriteWeather()
    }
}