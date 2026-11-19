package com.tunc.wayfolk.core.network

import io.ktor.client.engine.HttpClientEngineFactory

internal expect val httpEngineFactory: HttpClientEngineFactory<*>

internal expect fun Throwable.isNoInternetConnection(): Boolean
