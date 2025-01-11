package com.example.nalssi.domain.usecases.weather

import com.example.nalssi.data.DataState
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.repositories.IWeatherRepository
import kotlinx.coroutines.flow.Flow

class FetchDetailWeatherUseCase (private val weatherRepository: IWeatherRepository) {
    suspend operator fun invoke(q: String, forceFetch: Boolean = false): Flow<DataState<WeatherItem>> {
        return weatherRepository.fetchDetailWeather(q, forceFetch)
    }
}