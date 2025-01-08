package com.example.nalssi.domain.entities

data class WeatherItem (
    var custom_id: String? = null,
    var q: String? = null,
    var location: WeatherLocation? = null,
    var current: WeatherCurrent? = null
)