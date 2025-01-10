package com.example.nalssi.presentation.callback

import com.example.nalssi.domain.entities.weather.WeatherItem

interface HomeScreenCallback {
    fun onItemClicked(weatherItem: WeatherItem)
    fun onFavoriteClicked()
    fun onProfileClicked()
    fun onSettingsClicked()
}