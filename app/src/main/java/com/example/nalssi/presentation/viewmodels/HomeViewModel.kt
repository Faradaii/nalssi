package com.example.nalssi.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nalssi.data.DataState
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.usecases.weather.FetchAllWeatherUseCase
import com.example.nalssi.domain.usecases.weather.SearchWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel (private val fetchAllWeatherUseCase: FetchAllWeatherUseCase): ViewModel(), IHomeViewModel {

    init {
        fetchAllWeather()
    }

    private val _listWeather: MutableStateFlow<DataState<List<WeatherItem>>> by lazy {
        MutableStateFlow(DataState.Loading)
    }
    override val listWeather: StateFlow<DataState<List<WeatherItem>>> get() = _listWeather

    override fun fetchAllWeather() {
        viewModelScope.launch {
            try {
                fetchAllWeatherUseCase.invoke().collect { result ->
                    Log.d("HomeViewModel", "Result: $result")
                    _listWeather.value = when (result) {
                        is DataState.Success -> DataState.Success(result.data)
                        is DataState.Cached -> DataState.Cached(result.data)
                        is DataState.Error -> DataState.Error(result.errorMessage)
                        is DataState.Loading -> DataState.Loading
                    }
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error in fetchAllWeather: ${e.message}", e)
                _listWeather.value = DataState.Error("Unexpected error occurred.")
            }
        }
    }
}