package com.example.nalssi.data.datasources.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.nalssi.data.datasources.local.models.WeatherModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    fun fetchAllWeather(): Flow<List<WeatherModel>>

    @Delete
    suspend fun deleteAllWeather()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: List<WeatherModel>)

    @Update
    suspend fun updateWeather(weather: WeatherModel)
}