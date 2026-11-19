package com.tunc.wayfolk.core.network

data class NetworkConfig(
    val baseUrl: String,
    val isLoggingEnabled: Boolean = false,
    val requestTimeoutMillis: Long = 30_000L,
)
