package com.example.nalssi.data.repositories

import com.example.nalssi.data.DataState
import com.example.nalssi.data.datasources.local.LocalDataSource
import com.example.nalssi.data.datasources.local.database.WeatherDatabase
import com.example.nalssi.data.datasources.remote.RemoteDataSource
import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import com.example.nalssi.data.datasources.remote.network.ApiService
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.repositories.IWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class WeatherRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): IWeatherRepository {
    override suspend fun fetchAllWeather(): Flow<DataState<ListWeatherResponse>> {
        return flow {
            emit(DataState.Loading)
        }.flowOn(Dispatchers.IO)
    }

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