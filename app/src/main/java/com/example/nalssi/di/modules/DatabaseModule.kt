package com.example.nalssi.di.modules

import androidx.room.Room
import com.example.nalssi.data.datasources.local.database.WeatherDatabase
import org.koin.dsl.module

val DatabaseModule = module {
    single { Room.databaseBuilder(get(), WeatherDatabase::class.java, "weather.db").fallbackToDestructiveMigration().build() }
}