package com.example.nalssi.domain.entities

data class WeatherCurrent (
    var lastUpdated: String? = null,
    var temp_c: String? = null,
    var condition: WeatherCondition? = null,
    var feelslike_c: String? = null,
)

