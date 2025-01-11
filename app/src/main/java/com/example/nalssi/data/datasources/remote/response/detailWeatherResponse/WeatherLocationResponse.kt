package com.example.nalssi.data.datasources.remote.response.detailWeatherResponse

import com.google.gson.annotations.SerializedName

data class WeatherLocationResponse(

	@field:SerializedName("localtime")
	val localtime: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("localtime_epoch")
	val localtimeEpoch: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("lon")
	val lon: Any? = null,

	@field:SerializedName("region")
	val region: String? = null,

	@field:SerializedName("lat")
	val lat: Any? = null,

	@field:SerializedName("tz_id")
	val tzId: String? = null
)