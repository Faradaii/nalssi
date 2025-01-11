package com.example.nalssi.domain.repositories

import com.example.nalssi.data.DataState
import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import com.example.nalssi.domain.entities.weather.WeatherItem
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    suspend fun fetchAllWeather(forceFetch: Boolean = false): Flow<DataState<List<WeatherItem>>>
    suspend fun fetchDetailWeather(q: String, forceFetch: Boolean = false): Flow<DataState<WeatherItem>>
    suspend fun toggleFavoriteWeather(weatherItem: WeatherItem)
    suspend fun searchWeather(query: String): Flow<List<WeatherItem>>
    suspend fun getAllFavoriteWeather(): Flow<List<WeatherItem>>
}