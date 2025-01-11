package com.example.nalssi.presentation.viewmodels

import com.example.nalssi.data.DataState
import com.example.nalssi.domain.entities.weather.WeatherItem
import kotlinx.coroutines.flow.StateFlow

interface IFavoriteViewModel {
    val listWeather: StateFlow<List<WeatherItem>>

    fun fetchFavoriteWeather()
}