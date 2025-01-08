package com.example.nalssi.domain.repositories

import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import com.example.nalssi.data.datasources.remote.network.ApiResponse
import com.example.nalssi.domain.entities.weather.WeatherItem
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    suspend fun fetchAllWeather(): Flow<ApiResponse<ListWeatherResponse>>
    suspend fun fetchDetailWeather(id: String)
    suspend fun searchWeather(query: String)
    suspend fun getAllFavoriteWeather()
    suspend fun insertFavoriteWeather(weather: WeatherItem)
    suspend fun deleteFavoriteWeather(weather: WeatherItem)
}