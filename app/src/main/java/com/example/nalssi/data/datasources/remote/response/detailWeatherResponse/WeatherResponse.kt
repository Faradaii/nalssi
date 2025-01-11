package com.example.nalssi.data.datasources.remote.response.detailWeatherResponse

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

	@field:SerializedName("current")
	val current: WeatherCurrentResponse? = null,

	@field:SerializedName("location")
	val location: WeatherLocationResponse? = null
)

