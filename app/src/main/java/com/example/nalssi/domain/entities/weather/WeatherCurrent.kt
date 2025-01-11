package com.example.nalssi.domain.entities.weather

data class WeatherCurrent (
    var lastUpdated: String? = null,
    var tempC: String? = null,
    var tempF: String? = null,
    var windMph: String? = null,
    var windDir: String? = null,
    var pressureMb: String? = null,
    var precipitationMm: String? = null,
    var humidity: String? = null,
    var cloud: String? = null,
    var uv: String? = null,
    var condition: WeatherCondition? = null,
    var feelsLikeC: String? = null,
)

