package com.example.nalssi.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.usecases.weather.GetAllFavoriteWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel (private val getAllFavoriteWeatherUseCase: GetAllFavoriteWeatherUseCase): ViewModel(), IFavoriteViewModel {

    init {
        fetchFavoriteWeather()
    }

    private val _listWeather: MutableStateFlow<List<WeatherItem>> = MutableStateFlow(emptyList())
    override val listWeather: StateFlow<List<WeatherItem>> get() = _listWeather

    override fun fetchFavoriteWeather() {
        viewModelScope.launch {
            try {
                getAllFavoriteWeatherUseCase.invoke().collect { result ->
                    Log.d("HomeViewModel", "Result: $result")
                    _listWeather.value = result
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error in fetchAllWeather: ${e.message}", e)
                _listWeather.value = emptyList()
            }
        }
    }
}