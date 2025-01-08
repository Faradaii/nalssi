package com.example.nalssi.domain.usecases.favorite

import com.example.nalssi.domain.entities.WeatherItem
import com.example.nalssi.domain.repositories.IFavoriteRepository

class DeleteFavoriteUseCase (private val IFavoriteRepository: IFavoriteRepository) {
    suspend fun execute(weather: WeatherItem) {
        IFavoriteRepository.deleteFavorite(weather)
    }
}