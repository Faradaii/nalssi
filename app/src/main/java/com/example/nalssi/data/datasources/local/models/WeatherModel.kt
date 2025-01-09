package com.example.nalssi.data.datasources.local.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "custom_id")
    val custom_id: String? = null,

    @ColumnInfo(name = "q")
    var q: String? = null,

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

    @ColumnInfo(name = "tz_id")
    var localTime: String? = null
)

data class WeatherCurrentModel (
    @ColumnInfo(name = "last_updated")
    var lastUpdated: String? = null,

    @ColumnInfo(name = "temp_c")
    var temp_c: String? = null,

    @ColumnInfo(name = "condition")
    var condition: WeatherConditionModel? = null,

    @ColumnInfo(name = "wind_kph")
    var feelslike_c: String? = null,
)

data class WeatherConditionModel (
    @ColumnInfo(name = "text")
    var text: String? = null,

    @ColumnInfo(name = "icon")
    var icon: String? = null,

    @ColumnInfo(name = "code")
    var code: String? = null
)