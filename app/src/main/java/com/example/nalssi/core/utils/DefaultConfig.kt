package com.example.nalssi.core.utils

import android.content.Context
import org.json.JSONObject
import java.io.InputStream

object DefaultConfig {
    private lateinit var json: JSONObject
    lateinit var apiKey: String
    lateinit var baseUrl: String

    fun initialize(context: Context) {
        val inputStream: InputStream = context.assets.open("config.json")
        json = JSONObject(inputStream.bufferedReader().use { it.readText() })
        apiKey = json.getString("API_KEY")
        baseUrl = json.getString("BASE_URL")
    }
}