package com.tunc.wayfolk.core.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.engine.darwin.DarwinHttpRequestException
import platform.Foundation.NSURLErrorCannotConnectToHost
import platform.Foundation.NSURLErrorCannotFindHost
import platform.Foundation.NSURLErrorDNSLookupFailed
import platform.Foundation.NSURLErrorDataNotAllowed
import platform.Foundation.NSURLErrorDomain
import platform.Foundation.NSURLErrorInternationalRoamingOff
import platform.Foundation.NSURLErrorNetworkConnectionLost
import platform.Foundation.NSURLErrorNotConnectedToInternet

internal actual val httpEngineFactory: HttpClientEngineFactory<*> = Darwin

private val noInternetErrorCodes = setOf(
    NSURLErrorNotConnectedToInternet,
    NSURLErrorNetworkConnectionLost,
    NSURLErrorCannotFindHost,
    NSURLErrorCannotConnectToHost,
    NSURLErrorDNSLookupFailed,
    NSURLErrorDataNotAllowed,
    NSURLErrorInternationalRoamingOff,
)

internal actual fun Throwable.isNoInternetConnection(): Boolean =
    generateSequence(this) { it.cause }.any { throwable ->
        throwable is DarwinHttpRequestException &&
            throwable.origin.domain == NSURLErrorDomain &&
            throwable.origin.code in noInternetErrorCodes
    }
