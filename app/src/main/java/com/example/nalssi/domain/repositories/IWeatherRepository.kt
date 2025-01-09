package com.example.nalssi.domain.repositories

import com.example.nalssi.data.DataState
import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import com.example.nalssi.domain.entities.weather.WeatherItem
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    suspend fun fetchAllWeather(): Flow<DataState<List<WeatherItem>>>
    fun fetchDetailWeather(id: String)
    fun searchWeather(query: String)
    fun getAllFavoriteWeather()
    fun insertFavoriteWeather(weather: WeatherItem)
    fun deleteFavoriteWeather(weather: WeatherItem)
}