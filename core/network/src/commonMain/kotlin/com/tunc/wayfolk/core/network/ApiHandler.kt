package com.tunc.wayfolk.core.network

import com.tunc.wayfolk.contract.network.NetworkErrorType
import com.tunc.wayfolk.contract.network.RestResult
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

class ApiHandler internal constructor(
    private val json: Json,
) {

    fun <T : Any> safeApiCall(call: suspend () -> T): Flow<RestResult<T>> = flow {
        emit(RestResult.Loading(true))
        emit(RestResult.Success(call()))
    }
        .catch { throwable ->
            if (throwable is CancellationException) throw throwable
            emit(RestResult.Error(throwable.toNetworkError()))
        }
        .onCompletion { cause ->
            if (cause == null) emit(RestResult.Loading(false))
        }

    private suspend fun Throwable.toNetworkError(): NetworkError = when {
        this is ResponseException -> NetworkError(
            message = message,
            data = json.parseOrNull(response.bodyAsText()),
            code = response.status.value,
        )

        isNoInternetConnection() -> NetworkError(
            message = message,
            code = NetworkErrorType.NO_INTERNET_CONNECTION.value,
        )

        this is HttpRequestTimeoutException -> NetworkError(
            message = message,
            code = NetworkErrorType.REQUEST_FAILED.value,
        )

        this is SerializationException -> NetworkError(
            message = message,
            code = NetworkErrorType.REQUEST_FAILED.value,
        )

        else -> NetworkError(
            message = message,
            code = NetworkErrorType.OTHER.value,
        )
    }

    private fun Json.parseOrNull(text: String): JsonElement? =
        runCatching { parseToJsonElement(text) }.getOrNull()
}
