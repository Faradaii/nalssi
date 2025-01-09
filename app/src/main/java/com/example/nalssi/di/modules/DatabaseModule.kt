package com.example.nalssi.di.modules

import androidx.room.Room
import com.example.nalssi.core.constant.DATABASE_NAME
import com.example.nalssi.data.datasources.local.database.WeatherDao
import com.example.nalssi.data.datasources.local.database.WeatherDatabase
import org.koin.dsl.module

val DatabaseModule = module {
    single { Room.databaseBuilder(get(), WeatherDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build() }
    single { get<WeatherDatabase>().weatherDao() }
    single { WeatherDao::class.java }
}