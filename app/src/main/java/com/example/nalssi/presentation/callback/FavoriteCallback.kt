package com.example.nalssi.presentation.callback

import com.example.nalssi.domain.entities.weather.WeatherItem

interface FavoriteScreenCallback {
    fun onItemClicked(weatherItem: WeatherItem)
    fun onBackClicked()
}