package com.tunc.wayfolk.core.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.UnknownHostException

internal actual val httpEngineFactory: HttpClientEngineFactory<*> = OkHttp

internal actual fun Throwable.isNoInternetConnection(): Boolean =
    generateSequence(this) { it.cause }.any {
        it is UnknownHostException || it is ConnectException || it is NoRouteToHostException
    }
