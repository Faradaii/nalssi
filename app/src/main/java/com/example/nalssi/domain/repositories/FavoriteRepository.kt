package com.example.nalssi.domain.repositories

import com.example.nalssi.domain.entities.WeatherItem

interface FavoriteRepository {
    suspend fun fetchAllFavorite()
    suspend fun insertFavorite(weather: WeatherItem)
    suspend fun deleteFavorite(weather: WeatherItem)
}