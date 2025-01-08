package com.example.nalssi.data.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nalssi.data.datasources.local.models.WeatherModel

@Database(entities = [WeatherModel::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}