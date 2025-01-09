package com.example.nalssi.core.utils

import android.content.Context
import android.util.Log
import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.ExperimentalHoplite
import com.sksamuel.hoplite.addResourceSource
import com.sksamuel.hoplite.sources.EnvironmentVariablesPropertySource
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