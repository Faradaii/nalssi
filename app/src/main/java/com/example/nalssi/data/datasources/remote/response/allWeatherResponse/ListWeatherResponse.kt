package com.example.nalssi.data.datasources.remote.response.allWeatherResponse

import com.google.gson.annotations.SerializedName

data class ListWeatherResponse(

	@field:SerializedName("bulk")
	val bulk: List<BulkItemResponse?>? = null
)

