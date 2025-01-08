package com.example.nalssi.domain.entities.weather

data class WeatherItem (
    var custom_id: String? = null,
    var q: String? = null,
    var location: WeatherLocation? = null,
    var current: WeatherCurrent? = null,
    var isFavorite: Boolean = false,
)