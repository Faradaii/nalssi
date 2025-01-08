package com.example.nalssi.core.utils

import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import com.example.nalssi.data.datasources.local.models.WeatherConditionModel
import com.example.nalssi.data.datasources.local.models.WeatherCurrentModel
import com.example.nalssi.data.datasources.local.models.WeatherLocationModel
import com.example.nalssi.data.datasources.local.models.WeatherModel
import com.example.nalssi.domain.entities.weather.WeatherCondition
import com.example.nalssi.domain.entities.weather.WeatherCurrent
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.entities.weather.WeatherLocation

object WeatherDataMapper {

    fun mapResponsesToEntities(input: ListWeatherResponse): List<WeatherModel> {
        val weatherEntityList = ArrayList<WeatherModel>()

        input.bulk?.map { response ->
            response?.queryResponse?.let { itemIndex ->
                val weatherModel = WeatherModel(
                    custom_id = itemIndex.customId,
                    q = itemIndex.q,
                    location = itemIndex.location?.let {
                        WeatherLocationModel(
                            name = it.name,
                            region = it.region,
                            country = it.country,
                            lat = it.lat?.toString(),
                            lon = it.lon?.toString(),
                            localTime = it.localtime
                        )
                    },
                    current = itemIndex.current?.let {
                        WeatherCurrentModel(
                            lastUpdated = it.lastUpdated,
                            temp_c = it.tempC?.toString(),
                            condition = it.condition?.let { condition ->
                                WeatherConditionModel(
                                    text = condition.text,
                                    icon = condition.icon,
                                    code = condition.code?.toString()
                                )
                            },
                            feelslike_c = it.feelslikeC?.toString()
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
        input.map {
            WeatherItem(
                custom_id = it.custom_id,
                q = it.q,
                location = WeatherLocation(
                    name = it.location?.name,
                    region = it.location?.region,
                    country = it.location?.country,
                    lat = it.location?.lat,
                    lon = it.location?.lon,
                    localTime = it.location?.localTime
                ),
                current = WeatherCurrent(
                    lastUpdated = it.current?.lastUpdated,
                    temp_c = it.current?.temp_c,
                    condition = WeatherCondition(
                        text = it.current?.condition?.text,
                        icon = it.current?.condition?.icon,
                        code = it.current?.condition?.code
                    ),
                    feelslike_c = it.current?.feelslike_c
                ),
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: WeatherItem): WeatherModel =
        WeatherModel(
            custom_id = input.custom_id,
            q = input.q,
            location = WeatherLocationModel(
                name = input.location?.name,
                region = input.location?.region,
                country = input.location?.country,
                lat = input.location?.lat,
                lon = input.location?.lon,
                localTime = input.location?.localTime
            ),
            current = WeatherCurrentModel(
                lastUpdated = input.current?.lastUpdated,
                temp_c = input.current?.temp_c,
                condition = WeatherConditionModel(
                    text = input.current?.condition?.text,
                    icon = input.current?.condition?.icon,
                    code = input.current?.condition?.code
                ),
                feelslike_c = input.current?.feelslike_c
            ),
            isFavorite = input.isFavorite
        )
}
