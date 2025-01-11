package com.example.nalssi.data.datasources.remote.network

import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import com.example.nalssi.data.datasources.remote.response.detailWeatherResponse.WeatherResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("current.json")
    suspend fun getAllWeather(
        @Query("key") key: String,
        @Query("q") query: String? = "bulk",
        @Body body: RequestBody
    ): ListWeatherResponse

    @POST("current.json")
    suspend fun getDetailWeather(
        @Query("key") key: String,
        @Query("q") q: String,
    ): WeatherResponse
}