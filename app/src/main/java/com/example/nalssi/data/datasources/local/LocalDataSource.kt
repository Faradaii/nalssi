package com.example.nalssi.data.datasources.local

import com.example.nalssi.data.datasources.local.database.WeatherDao
import com.example.nalssi.data.datasources.local.models.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSource (private val weatherDao: WeatherDao) {
    fun fetchAllWeather(): Flow<List<WeatherModel>> = weatherDao.fetchAllWeather()
    suspend fun insertWeather(weatherList: List<WeatherModel>) = weatherDao.insertWeather(weatherList)
    suspend fun getLastUpdatedDate(): String? = withContext(Dispatchers.IO) { weatherDao.getLastUpdatedDate() }
}