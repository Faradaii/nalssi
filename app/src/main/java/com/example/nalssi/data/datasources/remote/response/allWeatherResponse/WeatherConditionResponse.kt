package com.example.nalssi.data.datasources.remote.response.allWeatherResponse

import com.google.gson.annotations.SerializedName

data class WeatherConditionResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)