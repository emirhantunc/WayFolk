package com.tunc.wayfolk.core.network

import com.tunc.wayfolk.logger.WayFolkLogger
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object HttpClientFactory {

    private const val LOG_TAG = "WayFolkHttp"

    fun create(config: NetworkConfig, json: Json): HttpClient = HttpClient(httpEngineFactory) {
        expectSuccess = true

        defaultRequest {
            url(config.baseUrl)
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(json)
        }

        install(HttpTimeout) {
            requestTimeoutMillis = config.requestTimeoutMillis
            connectTimeoutMillis = config.requestTimeoutMillis
            socketTimeoutMillis = config.requestTimeoutMillis
        }

        if (config.isLoggingEnabled) {
            install(Logging) {
                level = LogLevel.BODY
                logger = object : Logger {
                    override fun log(message: String) {
                        WayFolkLogger.d(message = message, tag = LOG_TAG)
                    }
                }
            }
        }
    }
}
