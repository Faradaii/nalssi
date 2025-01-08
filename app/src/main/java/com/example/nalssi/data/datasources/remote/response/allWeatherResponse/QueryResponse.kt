package com.example.nalssi.data.datasources.remote.response.allWeatherResponse

import com.google.gson.annotations.SerializedName

data class QueryResponse(

    @field:SerializedName("q")
	val q: String? = null,

    @field:SerializedName("current")
	val current: CurrentWeatherResponse? = null,

    @field:SerializedName("custom_id")
	val customId: String? = null,

    @field:SerializedName("location")
	val location: WeatherLocationResponse? = null
)