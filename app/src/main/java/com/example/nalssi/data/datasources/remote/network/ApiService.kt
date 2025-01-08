package com.example.nalssi.data.datasources.remote.network

import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.Query

interface ApiService {
    @HTTP(method = "GET", path = "current.json", hasBody = true)
    suspend fun getAllWeather(
        @Query("q") query: String? = "bulk",
        @Body body: RequestBody
    ): ListWeatherResponse
}