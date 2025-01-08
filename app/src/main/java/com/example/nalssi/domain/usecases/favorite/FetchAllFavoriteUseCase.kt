package com.example.nalssi.domain.usecases.favorite

import com.example.nalssi.domain.repositories.FavoriteRepository

class FetchAllFavoriteUseCase (private val favoriteRepository: FavoriteRepository) {
    suspend fun execute() {
        favoriteRepository.fetchAllFavorite()
    }
}