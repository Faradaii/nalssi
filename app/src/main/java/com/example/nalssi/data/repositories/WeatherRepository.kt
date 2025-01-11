package com.example.nalssi.data.repositories

import android.util.Log
import com.example.nalssi.core.utils.DateUtil
import com.example.nalssi.core.utils.WeatherHelper
import com.example.nalssi.data.DataState
import com.example.nalssi.data.NetworkBoundResource
import com.example.nalssi.data.datasources.local.LocalDataSource
import com.example.nalssi.data.datasources.remote.RemoteDataSource
import com.example.nalssi.data.datasources.remote.network.ApiResponse
import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import com.example.nalssi.data.datasources.remote.response.detailWeatherResponse.WeatherResponse
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.repositories.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class WeatherRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): IWeatherRepository {
    override suspend fun fetchAllWeather(forceFetch: Boolean): Flow<DataState<List<WeatherItem>>> =
        object: NetworkBoundResource<List<WeatherItem>, ListWeatherResponse>() {
            override fun loadFromDb(): Flow<List<WeatherItem>> {
                return localDataSource.fetchAllWeather().map { WeatherHelper.mapEntitiesToDomain(it) }
            }

            override suspend fun getLastUpdatedDate(): Date? {
                val lastUpdatedDate = localDataSource.getLastUpdatedDate()
                return lastUpdatedDate?.let { DateUtil.stringToDate(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<ListWeatherResponse>> {
                Log.i("DEBUG", "FETCHING NEW DATA")
                return remoteDataSource.fetchAllWeather()
            }

            override suspend fun saveCallResult(data: ListWeatherResponse) {
                val weatherList = WeatherHelper.mapResponsesToEntities(data)
                localDataSource.insertAllWeather(weatherList)
            }

            override fun shouldFetch(data: List<WeatherItem>?, lastUpdatedDate: Date?): Boolean {
                return if(forceFetch){
                    true
                } else if (data != null) {
                    lastUpdatedDate?.let {
                        val diff = DateUtil.getDiffInDays(it, Date())
                        diff >= 1
                    } ?: true
                } else {
                    true
                }
            }
        }.asFlow()

    override suspend fun fetchDetailWeather(q: String, forceFetch: Boolean): Flow<DataState<WeatherItem>> =
        object: NetworkBoundResource<WeatherItem, WeatherResponse>() {
            override fun loadFromDb(): Flow<WeatherItem> {
                return localDataSource.fetchDetailWeather(q).map { WeatherHelper.mapEntityToDomain(it) }
            }

            override suspend fun getLastUpdatedDate(): Date? {
                val lastUpdatedDate = localDataSource.getLastUpdatedDateByQ(q)
                return lastUpdatedDate?.let { DateUtil.stringToDate(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<WeatherResponse>> {
                return remoteDataSource.fetchDetailWeather(q)
            }

            override suspend fun saveCallResult(data: WeatherResponse) {
                val weather = WeatherHelper.mapResponseToEntity(data)
                localDataSource.updateWeather(weather)
            }

            override fun shouldFetch(data: WeatherItem?, lastUpdatedDate: Date?): Boolean {
                return if(forceFetch){
                    true
                } else if (data != null) {
                    lastUpdatedDate?.let {
                        val diff = DateUtil.getDiffInDays(it, Date())
                        diff >= 1
                    } ?: true
                } else {
                    true
                }
            }
        }.asFlow()

    override fun searchWeather(query: String) {
        //TODO("Not yet implemented")
    }

    override fun getAllFavoriteWeather() {
        //TODO("Not yet implemented")
    }

    override suspend fun toggleFavoriteWeather(weather: WeatherItem) {
        val isFavorite = !weather.isFavorite
        val q = weather.q ?: ""
        Log.d("Q", q)
        localDataSource.updateWeatherFavorite(q, isFavorite)
    }

}