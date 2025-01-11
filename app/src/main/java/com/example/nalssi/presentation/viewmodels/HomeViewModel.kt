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

class HomeViewModel (
    private val fetchAllWeatherUseCase: FetchAllWeatherUseCase,
    private val searchWeatherUseCase: SearchWeatherUseCase,
): ViewModel(), IHomeViewModel {

    init {
        fetchAllWeather()
    }

    private val _listWeather: MutableStateFlow<DataState<List<WeatherItem>>> = MutableStateFlow(DataState.Loading)
    override val listWeather: StateFlow<DataState<List<WeatherItem>>> get() = _listWeather

    private var cachedWeather: List<WeatherItem> = emptyList()

    override fun fetchAllWeather(forceFetch: Boolean) {
        viewModelScope.launch {
            try {
                fetchAllWeatherUseCase.invoke(forceFetch).collect { result ->
                    Log.d("HomeViewModel", "Result: $result")
                    _listWeather.value = when (result) {
                        is DataState.Success -> {
                            cachedWeather = result.data
                            DataState.Success(result.data)
                        }
                        is DataState.Cached -> {
                            cachedWeather = result.data
                            DataState.Cached(result.data)
                        }
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

    override fun searchWeather(q: String) {
        viewModelScope.launch {
            try {
                if (q.isBlank()) {
                    _listWeather.value = DataState.Cached(cachedWeather)
                } else {
                    searchWeatherUseCase.invoke(q).collect { result ->
                        _listWeather.value = DataState.Cached(result)
                    }
                }
            } catch (e: Exception) {
                _listWeather.value = DataState.Error("Search failed: ${e.message}")
            }
        }
    }
}