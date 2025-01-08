package com.example.nalssi.domain.repositories

interface IWeatherRepository {
    suspend fun fetchAllWeather()
    suspend fun fetchDetailWeather(id: String)
    suspend fun searchWeather(query: String)
}