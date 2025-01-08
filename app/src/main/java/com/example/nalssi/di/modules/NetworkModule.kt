package com.example.nalssi.di.modules

import com.example.nalssi.data.datasources.remote.network.ApiConfig
import com.example.nalssi.data.datasources.remote.network.ApiService
import org.koin.dsl.module

val NetworkModule = module {
    single { ApiConfig.provideApiService() }
    single { ApiService::class.java }
}