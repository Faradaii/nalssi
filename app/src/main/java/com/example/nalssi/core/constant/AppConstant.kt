package com.example.nalssi.core.constant

import android.util.Log
import com.example.nalssi.core.utils.DefaultConfig
import com.example.nalssi.core.utils.apiKey
import com.example.nalssi.core.utils.baseUrl

const val DATABASE_NAME = "weather.db"
const val DATABASE_VERSION = 2

//var BASE_URL: String = "https://api.weatherapi.com/v1/"
//var API_KEY: String = "e03ab3990b8f49b481e100122250401"

var BASE_URL: String = DefaultConfig.baseUrl
var API_KEY: String = DefaultConfig.apiKey