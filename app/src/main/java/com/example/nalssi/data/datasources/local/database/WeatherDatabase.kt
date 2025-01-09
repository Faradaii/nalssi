package com.example.nalssi.data.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nalssi.core.constant.DATABASE_VERSION
import com.example.nalssi.core.utils.RoomConverters
import com.example.nalssi.data.datasources.local.models.WeatherModel

@Database(entities = [WeatherModel::class], version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}