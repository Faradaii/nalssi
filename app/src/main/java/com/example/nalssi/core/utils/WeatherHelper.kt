package com.example.nalssi.core.utils

import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import com.example.nalssi.data.datasources.local.models.WeatherConditionModel
import com.example.nalssi.data.datasources.local.models.WeatherCurrentModel
import com.example.nalssi.data.datasources.local.models.WeatherLocationModel
import com.example.nalssi.data.datasources.local.models.WeatherModel
import com.example.nalssi.data.datasources.remote.response.detailWeatherResponse.WeatherResponse
import com.example.nalssi.domain.entities.weather.WeatherCondition
import com.example.nalssi.domain.entities.weather.WeatherCurrent
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.entities.weather.WeatherLocation

object WeatherHelper {

    private val directionMap = mapOf(
        "N" to "North",
        "NNE" to "North-Northeast",
        "NE" to "Northeast",
        "ENE" to "East-Northeast",
        "E" to "East",
        "ESE" to "East-Southeast",
        "SE" to "Southeast",
        "SSE" to "South-Southeast",
        "S" to "South",
        "SSW" to "South-Southwest",
        "SW" to "Southwest",
        "WSW" to "West-Southwest",
        "W" to "West",
        "WNW" to "West-Northwest",
        "NW" to "Northwest",
        "NNW" to "North-Northwest"
    )

    fun getReadableDirection(windDir: String): String {
        return directionMap[windDir] ?: "Unknown Direction"
    }

    fun mapEntityToDomain(input: WeatherModel): WeatherItem =
        WeatherItem(
            location = input.location?.let {
                WeatherLocation(
                    name = it.name,
                    region = it.region,
                    country = it.country,
                    lat = it.lat,
                    lon = it.lon,
                    localTime = it.localTime,
                    timezone = it.timezoneId
                )
            },
            current = input.current?.let {
                WeatherCurrent(
                    lastUpdated = it.lastUpdated,
                    tempC = it.tempC,
                    tempF = it.tempF,
                    feelsLikeC = it.feelsLikeC,
                    windMph = it.windMph,
                    windDir = it.windDir,
                    pressureMb = it.pressureMb,
                    precipitationMm = it.precipitationMm,
                    humidity = it.humidity,
                    cloud = it.cloud,
                    uv = it.uv,
                    condition = it.condition?.let { condition ->
                        WeatherCondition(
                            text = condition.text,
                            icon = condition.icon,
                            code = condition.code
                        )
                    },
                )
            },
            isFavorite = input.isFavorite
        )

    fun mapResponseToEntity(input: WeatherResponse): WeatherModel =
        WeatherModel(
            uniqKey = "${ input.location?.name }-${ input.location?.region }",
            location = input.location?.let {
                WeatherLocationModel(
                    name = it.name,
                    region = it.region,
                    country = it.country,
                    lat = it.lat?.toString(),
                    lon = it.lon?.toString(),
                    localTime = it.localtime,
                    timezoneId = it.tzId,
                )
            },
            current = input.current?.let {
                WeatherCurrentModel(
                    lastUpdated = it.lastUpdated,
                    tempC = it.tempC?.toString(),
                    tempF = it.tempF?.toString(),
                    windMph = it.windMph?.toString(),
                    windDir = it.windDir,
                    pressureMb = it.pressureMb?.toString(),
                    precipitationMm = it.precipMm?.toString(),
                    humidity = it.humidity?.toString(),
                    cloud = it.cloud?.toString(),
                    uv = it.uv?.toString(),
                    condition = it.condition?.let { condition ->
                        WeatherConditionModel(
                            text = condition.text,
                            icon = condition.icon,
                            code = condition.code?.toString()
                        )
                    },
                    feelsLikeC = it.feelslikeC?.toString()
                )
            },
            isFavorite = false
    )

    fun mapResponsesToEntities(input: ListWeatherResponse): List<WeatherModel> {
        val weatherEntityList = ArrayList<WeatherModel>()

        input.bulk?.map { response ->
            response?.queryResponse?.let { itemIndex ->
                val weatherModel = WeatherModel(
                    uniqKey = "${ itemIndex.location?.name }-${ itemIndex.location?.region }",
                    location = itemIndex.location?.let {
                        WeatherLocationModel(
                            name = it.name,
                            region = it.region,
                            country = it.country,
                            lat = it.lat?.toString(),
                            lon = it.lon?.toString(),
                            localTime = it.localtime,
                            timezoneId = it.tzId
                        )
                    },
                    current = itemIndex.current?.let {
                        WeatherCurrentModel(
                            lastUpdated = it.lastUpdated,
                            tempC = it.tempC?.toString(),
                            tempF = it.tempF?.toString(),
                            windMph = it.windMph?.toString(),
                            windDir = it.windDir,
                            pressureMb = it.pressureMb?.toString(),
                            precipitationMm = it.precipMm?.toString(),
                            humidity = it.humidity?.toString(),
                            cloud = it.cloud?.toString(),
                            uv = it.uv?.toString(),
                            condition = it.condition?.let { condition ->
                                WeatherConditionModel(
                                    text = condition.text,
                                    icon = condition.icon,
                                    code = condition.code?.toString()
                                )
                            },
                            feelsLikeC = it.feelslikeC?.toString()
                        )
                    },
                    isFavorite = false
                )
                weatherEntityList.add(weatherModel)
            }
        }
        return weatherEntityList
    }

    fun mapEntitiesToDomain(input: List<WeatherModel>): List<WeatherItem> =
        input.map { mapEntityToDomain(it) }

}
