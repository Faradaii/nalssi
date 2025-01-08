package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.repositories.IWeatherRepository

class FetchAllWeatherUseCase(private val IWeatherRepository: IWeatherRepository) {
    suspend fun execute(){
        IWeatherRepository.fetchAllWeather()
    }
}