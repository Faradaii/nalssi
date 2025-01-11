package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.repositories.IWeatherRepository
import kotlinx.coroutines.flow.Flow

class SearchWeatherUseCase (private val weatherRepository: IWeatherRepository) {
    suspend fun invoke(query: String): Flow<List<WeatherItem>> {
        return weatherRepository.searchWeather(query)
    }
}