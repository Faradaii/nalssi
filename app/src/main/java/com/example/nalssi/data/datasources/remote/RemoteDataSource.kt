package com.example.nalssi.data.datasources.remote

import android.content.Context
import android.util.Log
import com.example.nalssi.core.constant.API_KEY
import com.example.nalssi.data.datasources.remote.network.ApiResponse
import com.example.nalssi.data.datasources.remote.network.ApiService
import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import com.example.nalssi.data.datasources.remote.response.detailWeatherResponse.WeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class RemoteDataSource (private val apiService: ApiService, private val context: Context) {
    private val apiKey = API_KEY
    fun fetchAllWeather(): Flow<ApiResponse<ListWeatherResponse>> {
        return flow {
            Log.d("DEBUG", "FETCHING NEW DATA NETWORK")
            try {
                val jsonBody = context.assets.open("province_data.json").bufferedReader().use { it.readText() }
                val requestBody = jsonBody.toRequestBody("application/json".toMediaType())

                val response = apiService.getAllWeather(body = requestBody, key = apiKey)
                if (response.bulk != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Error("Data not found"))
                }
            } catch (e: Exception) {
                throw e
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.message ?: "Unknown error occurred"))
        }
    }

    fun fetchDetailWeather(q: String): Flow<ApiResponse<WeatherResponse>> {
        return flow {
            Log.d("DEBUG", "FETCHING NEW DATA NETWORK")
            try {
                val response = apiService.getDetailWeather(key = apiKey, q = q)
                if (response.current != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Error("Data not found"))
                }
            } catch (e: Exception) {
                throw e
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.message ?: "Unknown error occurred"))
        }
    }
}