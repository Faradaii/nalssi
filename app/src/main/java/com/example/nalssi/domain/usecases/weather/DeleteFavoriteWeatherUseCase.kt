package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.repositories.IWeatherRepository

class DeleteFavoriteWeatherUseCase (private val weatherRepository: IWeatherRepository) {
    suspend fun execute(weather: WeatherItem) {
        weatherRepository.deleteFavoriteWeather(weather)
    }
}