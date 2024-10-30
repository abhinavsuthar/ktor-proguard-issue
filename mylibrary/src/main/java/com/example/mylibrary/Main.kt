package com.example.mylibrary

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Main {

    @Serializable
    internal class LoginRequest(
        @SerialName("email") val email: String,
        @SerialName("device_info") val deviceInfo: String,
    )

    internal suspend fun apiCall() = withContext(Dispatchers.IO) {

        val client = HttpClient {
            install(ContentNegotiation) {
                json(json)
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }

        val req = LoginRequest("email", "device_info")

        val res = client.post {
            url("https://google.com")
            setBody(req)
        }

        Log.d(TAG, "apiCall: ${res.status}")
    }

    companion object {
        private const val TAG = "Main"
        fun start(): String {

            val dummy = LoginRequest("dummy@email", "device_info")
            val str = json.encodeToString(dummy)
            Log.d(TAG, "onCreate: $str")

            GlobalScope.launch {
                Main().apiCall()
            }

            return str
        }

        private val json by lazy {
            Json {
                isLenient = true
                explicitNulls = false
                encodeDefaults = true
                ignoreUnknownKeys = true
                useAlternativeNames = false
                useArrayPolymorphism = false
                coerceInputValues = true
            }
        }
    }
}