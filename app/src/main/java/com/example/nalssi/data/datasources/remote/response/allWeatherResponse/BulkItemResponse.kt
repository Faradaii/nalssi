package com.example.nalssi.data.datasources.remote.response.allWeatherResponse

import com.google.gson.annotations.SerializedName

data class BulkItemResponse(

	@field:SerializedName("query")
	val queryResponse: QueryResponse? = null
)