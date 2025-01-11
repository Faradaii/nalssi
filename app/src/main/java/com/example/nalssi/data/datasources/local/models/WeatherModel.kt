package com.example.nalssi.data.datasources.local.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherModel(
    @PrimaryKey
    val uniqKey: String = "",

    @Embedded(prefix = "location_")
    var location: WeatherLocationModel? = null,

    @Embedded(prefix = "current")
    var current: WeatherCurrentModel? = null,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false,
)

data class WeatherLocationModel (
    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "region")
    var region: String? = null,

    @ColumnInfo(name = "country")
    var country: String? = null,

    @ColumnInfo(name = "lat")
    var lat: String? = null,

    @ColumnInfo(name = "lon")
    var lon: String? = null,

    @ColumnInfo(name = "localtime")
    var localTime: String? = null,

    @ColumnInfo(name = "tz_id")
    var timezoneId: String? = null
)

data class WeatherCurrentModel (
    @ColumnInfo(name = "last_updated")
    var lastUpdated: String? = null,

    @ColumnInfo(name = "temp_c")
    var tempC: String? = null,

    @ColumnInfo(name = "temp_f")
    var tempF: String? = null,

    @ColumnInfo(name = "wind_mph")
    var windMph: String? = null,

    @ColumnInfo(name = "wind_dir")
    var windDir: String? = null,

    @ColumnInfo(name = "pressure_mb")
    var pressureMb: String? = null,

    @ColumnInfo(name = "precipitation_mm")
    var precipitationMm: String? = null,

    @ColumnInfo(name = "cloud")
    var cloud: String? = null,

    @ColumnInfo(name = "uv")
    var uv: String? = null,

    @ColumnInfo(name = "condition")
    var condition: WeatherConditionModel? = null,

    @ColumnInfo(name = "feels_like_c")
    var feelsLikeC: String? = null,

    @ColumnInfo(name = "humidity")
    var humidity: String? = null
)

data class WeatherConditionModel (
    @ColumnInfo(name = "text")
    var text: String? = null,

    @ColumnInfo(name = "icon")
    var icon: String? = null,

    @ColumnInfo(name = "code")
    var code: String? = null
)