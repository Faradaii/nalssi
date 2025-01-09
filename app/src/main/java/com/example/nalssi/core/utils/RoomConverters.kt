package com.example.nalssi.core.utils

import androidx.room.TypeConverter
import com.example.nalssi.data.datasources.local.models.WeatherConditionModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverters {

    @TypeConverter
    fun fromWeatherCondition(condition: WeatherConditionModel?): String {
        return Gson().toJson(condition)
    }

    @TypeConverter
    fun toWeatherCondition(conditionString: String): WeatherConditionModel? {
        val type = object : TypeToken<WeatherConditionModel?>() {}.type
        return Gson().fromJson(conditionString, type)
    }
}
