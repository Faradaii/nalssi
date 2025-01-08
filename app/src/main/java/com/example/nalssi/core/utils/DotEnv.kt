package com.example.nalssi.core.utils

import io.github.cdimascio.dotenv.dotenv

object DotEnv {
    private val dotenv = dotenv {
        directory = "/assets"
        filename = "env"
    }

    fun get(key: String): String = dotenv[key]
}