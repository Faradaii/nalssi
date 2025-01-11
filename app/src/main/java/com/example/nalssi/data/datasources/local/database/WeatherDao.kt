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

    @Query("SELECT * FROM weather WHERE is_favorite = 1")
    fun getAllFavoriteWeather(): Flow<List<WeatherModel>>

    @Query("SELECT * FROM weather WHERE uniqKey LIKE '%' || :q || '%'")
    fun searchWeather(q: String): Flow<List<WeatherModel>>

    @Delete
    suspend fun deleteAllWeather(weather: WeatherModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: List<WeatherModel>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWeather(weather: WeatherModel)

    @Query("UPDATE weather SET is_favorite = :isFavorite WHERE uniqKey = :q")
    suspend fun updateWeatherFavorite(q: String, isFavorite: Boolean)

    @Query("SELECT * FROM weather WHERE uniqKey = :q")
    fun getWeatherByQ(q: String): Flow<WeatherModel>

    @Query("SELECT currentlast_updated FROM weather ORDER BY currentlast_updated DESC LIMIT 1")
    fun getLastUpdatedDate(): String?

    @Query("SELECT currentlast_updated FROM weather WHERE uniqKey = :q ORDER BY currentlast_updated DESC LIMIT 1")
    fun getLastUpdatedDateByQ(q: String): String?
}