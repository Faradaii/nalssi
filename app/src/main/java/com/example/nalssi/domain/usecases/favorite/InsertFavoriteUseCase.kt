package com.example.nalssi.domain.usecases.favorite

import com.example.nalssi.domain.entities.WeatherItem
import com.example.nalssi.domain.repositories.FavoriteRepository

class InsertFavoriteUseCase (private val favoriteRepository: FavoriteRepository) {
    suspend fun execute(weather: WeatherItem) {
        favoriteRepository.insertFavorite(weather)
    }
}