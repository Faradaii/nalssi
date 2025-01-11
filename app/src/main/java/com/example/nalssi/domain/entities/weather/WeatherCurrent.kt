package com.example.nalssi.domain.entities.weather

data class WeatherCurrent (
    var lastUpdated: String? = null,
    var temp_c: String? = null,
    var temp_f: String? = null,
    var wind_mph: String? = null,
    var wind_dir: String? = null,
    var pressure_mb: String? = null,
    var precip_mm: String? = null,
    var humidity: String? = null,
    var cloud: String? = null,
    var uv: String? = null,
    var condition: WeatherCondition? = null,
    var feelslike_c: String? = null,
)

