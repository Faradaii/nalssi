package com.example.nalssi.domain.entities.weather

data class WeatherItem (
    var location: WeatherLocation? = null,
    var current: WeatherCurrent? = null,
    var isFavorite: Boolean = false,
)