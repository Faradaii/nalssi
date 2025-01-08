package com.example.nalssi.data.datasources.remote

import com.example.nalssi.data.datasources.remote.network.ApiService
import com.example.nalssi.data.datasources.remote.network.ApiResponse
import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class RemoteDataSource (private val apiService: ApiService) {
    suspend fun fetchAllWeather(): Flow<ApiResponse<ListWeatherResponse>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val jsonFilePath = "src/main/assets/province_data.json"
                val jsonBody = File(jsonFilePath).readText(Charsets.UTF_8)
                val requestBody = jsonBody.toRequestBody("application/json".toMediaType())

                val response = apiService.getAllWeather(body = requestBody)
                if (response.bulk != null) {
                    emit(ApiResponse.Success(response))
                }
                else {
                    emit(ApiResponse.Error("Data not found"))
                }
            }
            catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }
}