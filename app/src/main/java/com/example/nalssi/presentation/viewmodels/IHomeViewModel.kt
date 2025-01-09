package com.example.nalssi.presentation.viewmodels

import com.example.nalssi.data.DataState
import com.example.nalssi.domain.entities.weather.WeatherItem
import kotlinx.coroutines.flow.StateFlow

interface IHomeViewModel {
    val listWeather: StateFlow<DataState<List<WeatherItem>>>

    fun fetchAllWeather()
}