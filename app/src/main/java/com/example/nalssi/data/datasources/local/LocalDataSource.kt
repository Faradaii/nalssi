package com.example.nalssi.data.datasources.local

import com.example.nalssi.data.datasources.local.database.WeatherDao
import com.example.nalssi.data.datasources.local.models.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSource (private val weatherDao: WeatherDao) {
    fun fetchAllWeather(): Flow<List<WeatherModel>> = weatherDao.fetchAllWeather()
    fun fetchDetailWeather(q: String): Flow<WeatherModel> = weatherDao.getWeatherByQ(q)
    suspend fun insertAllWeather(weatherList: List<WeatherModel>) = weatherDao.insertWeather(weatherList)
    suspend fun updateWeather(weather: WeatherModel) = weatherDao.updateWeather(weather)
    suspend fun updateWeatherFavorite(q: String, isFavorite: Boolean) = weatherDao.updateWeatherFavorite(q, isFavorite)
    suspend fun deleteAllWeather(weather: WeatherModel) = weatherDao.deleteAllWeather(weather)
    suspend fun getLastUpdatedDate(): String? = withContext(Dispatchers.IO) { weatherDao.getLastUpdatedDate() }
    suspend fun getLastUpdatedDateByQ(q: String): String? = withContext(Dispatchers.IO) { weatherDao.getLastUpdatedDateByQ(q) }
}