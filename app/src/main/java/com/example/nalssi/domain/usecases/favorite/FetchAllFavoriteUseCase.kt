package com.example.nalssi.domain.usecases.favorite

import com.example.nalssi.domain.repositories.IFavoriteRepository

class FetchAllFavoriteUseCase (private val IFavoriteRepository: IFavoriteRepository) {
    suspend fun execute() {
        IFavoriteRepository.fetchAllFavorite()
    }
}