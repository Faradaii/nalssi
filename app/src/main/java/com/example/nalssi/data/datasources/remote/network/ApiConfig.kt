package com.example.nalssi.data.datasources.remote.network

import com.example.nalssi.core.constant.API_KEY
import com.example.nalssi.core.constant.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    private val baseUrl: String = BASE_URL
    private val apiKey: String = API_KEY

    private fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "key $apiKey")
                .addHeader("Accept", "application/json")
                .build()
            chain.proceed(request)
        }
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(provideInterceptor())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}