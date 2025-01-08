package com.example.nalssi.domain.usecases.favorite

import com.example.nalssi.domain.entities.WeatherItem
import com.example.nalssi.domain.repositories.FavoriteRepository

class DeleteFavoriteUseCase (private val favoriteRepository: FavoriteRepository) {
    suspend fun execute(weather: WeatherItem) {
        favoriteRepository.deleteFavorite(weather)
    }
}