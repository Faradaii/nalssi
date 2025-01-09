package com.example.nalssi.data.repositories

import com.example.nalssi.core.utils.DateUtil
import com.example.nalssi.core.utils.WeatherDataMapper
import com.example.nalssi.data.DataState
import com.example.nalssi.data.NetworkBoundResource
import com.example.nalssi.data.datasources.local.LocalDataSource
import com.example.nalssi.data.datasources.local.database.WeatherDatabase
import com.example.nalssi.data.datasources.remote.RemoteDataSource
import com.example.nalssi.data.datasources.remote.network.ApiResponse
import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import com.example.nalssi.data.datasources.remote.network.ApiService
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.repositories.IWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.util.Date

class WeatherRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): IWeatherRepository {
    override suspend fun fetchAllWeather(): Flow<DataState<List<WeatherItem>>> =
        object: NetworkBoundResource<List<WeatherItem>, ListWeatherResponse>() {
            override fun loadFromDb(): Flow<List<WeatherItem>> {
                return localDataSource.fetchAllWeather().map { WeatherDataMapper.mapEntitiesToDomain(it) }
            }

            override fun getLastUpdatedDate(): Date? {
                val lastUpdatedDate = localDataSource.getLastUpdatedDate()
                return DateUtil.stringToDate(lastUpdatedDate)
            }

            override suspend fun createCall(): Flow<ApiResponse<ListWeatherResponse>> {
                return remoteDataSource.fetchAllWeather()
            }

            override suspend fun saveCallResult(data: ListWeatherResponse) {
                val weatherList = WeatherDataMapper.mapResponsesToEntities(data)
                localDataSource.insertWeather(weatherList)
            }

            override fun shouldFetch(data: List<WeatherItem>?, lastUpdatedDate: Date?): Boolean {
                return if (data != null) {
                    lastUpdatedDate.let {
                        if (it == null) true
                        else {
                            val diff = DateUtil.getDiffInDays(it, Date())
                            diff >= 1
                        }
                    }
                }
                else { true }
            }
        }.asFlow()

    override suspend fun fetchDetailWeather(id: String) {
        //TODO("Not yet implemented")
    }

    override suspend fun searchWeather(query: String) {
        //TODO("Not yet implemented")
    }

    override suspend fun getAllFavoriteWeather() {
        //TODO("Not yet implemented")
    }

    override suspend fun insertFavoriteWeather(weather: WeatherItem) {
        //TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteWeather(weather: WeatherItem) {
        //TODO("Not yet implemented")
    }
}