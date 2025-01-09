package com.example.nalssi.data.datasources.remote.network

import com.example.nalssi.data.datasources.remote.response.allWeatherResponse.ListWeatherResponse
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
}