package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.repositories.IWeatherRepository

class FetchAllWeatherUseCase(private val weatherRepository: IWeatherRepository) {
    suspend fun execute(){
        weatherRepository.fetchAllWeather()
    }
}