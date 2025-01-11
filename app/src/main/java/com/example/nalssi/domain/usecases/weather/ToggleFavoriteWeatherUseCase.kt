package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.repositories.IWeatherRepository

class ToggleFavoriteWeatherUseCase (private val weatherRepository: IWeatherRepository) {
    suspend operator fun invoke(weather: WeatherItem) {
        weatherRepository.toggleFavoriteWeather(weather)
    }
}