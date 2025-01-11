package com.example.nalssi.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nalssi.data.DataState
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.domain.usecases.weather.FetchDetailWeatherUseCase
import com.example.nalssi.domain.usecases.weather.ToggleFavoriteWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val fetchDetailWeatherUseCase: FetchDetailWeatherUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteWeatherUseCase
): ViewModel(), IDetailViewModel{
    private val _detailWeather: MutableStateFlow<DataState<WeatherItem>> = MutableStateFlow(DataState.Loading)
    override val detailWeather: StateFlow<DataState<WeatherItem>> get() = _detailWeather

    override fun fetchDetailWeather(q: String, forceFetch: Boolean) {
        viewModelScope.launch {
            try {
                fetchDetailWeatherUseCase.invoke(q, forceFetch).collect { result ->
                    _detailWeather.value = when (result) {
                        is DataState.Success -> DataState.Success(result.data)
                        is DataState.Cached -> DataState.Cached(result.data)
                        is DataState.Error -> DataState.Error(result.errorMessage)
                        is DataState.Loading -> DataState.Loading
                    }
                }
            } catch (e: Exception) {
                _detailWeather.value = DataState.Error("Unexpected error occurred. $e")
            }
        }
    }

    override fun toggleFavoriteWeather(weather: WeatherItem?) {
        if (weather == null) return
        viewModelScope.launch {
            toggleFavoriteUseCase.invoke(weather)
        }
    }


}