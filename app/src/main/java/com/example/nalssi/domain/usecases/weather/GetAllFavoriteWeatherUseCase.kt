package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.data.DataState
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.repositories.IWeatherRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoriteWeatherUseCase (private val weatherRepository: IWeatherRepository) {
    suspend operator fun invoke(): Flow<List<WeatherItem>> {
        return weatherRepository.getAllFavoriteWeather()
    }
}