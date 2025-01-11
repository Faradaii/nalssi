package com.example.nalssi.presentation.viewmodels

import com.example.nalssi.data.DataState
import com.example.nalssi.domain.entities.weather.WeatherItem
import kotlinx.coroutines.flow.StateFlow

interface IDetailViewModel {
    val detailWeather: StateFlow<DataState<WeatherItem>>

    fun fetchDetailWeather(q: String, forceFetch: Boolean = false)

    fun toggleFavoriteWeather(weather: WeatherItem?)
}