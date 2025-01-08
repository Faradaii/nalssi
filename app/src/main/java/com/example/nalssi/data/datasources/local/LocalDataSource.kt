package com.example.nalssi.data.datasources.local

import com.example.nalssi.data.datasources.local.database.WeatherDao
import com.example.nalssi.data.datasources.local.models.WeatherModel
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val weatherDao: WeatherDao) {
    fun fetchAllWeather(): Flow<List<WeatherModel>> = weatherDao.fetchAllWeather()
}