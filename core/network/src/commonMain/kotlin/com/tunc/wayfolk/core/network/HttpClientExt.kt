package com.tunc.wayfolk.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

suspend inline fun <reified Response> HttpClient.sendRequest(
    endpoint: String,
    method: HttpMethod = HttpMethod.Get,
    queryParameters: Map<String, Any?> = emptyMap(),
    headers: Map<String, String> = emptyMap(),
): Response = request {
    configure(
        endpoint = endpoint,
        method = method,
        queryParameters = queryParameters,
        headers = headers,
    )
}.body()

suspend inline fun <reified Request, reified Response> HttpClient.sendRequest(
    endpoint: String,
    body: Request,
    method: HttpMethod = HttpMethod.Post,
    queryParameters: Map<String, Any?> = emptyMap(),
    headers: Map<String, String> = emptyMap(),
): Response = request {
    configure(
        endpoint = endpoint,
        method = method,
        queryParameters = queryParameters,
        headers = headers,
    )
    setBody(body)
}.body()

@PublishedApi
internal fun HttpRequestBuilder.configure(
    endpoint: String,
    method: HttpMethod,
    queryParameters: Map<String, Any?>,
    headers: Map<String, String>,
) {
    this.method = method
    url(endpoint)
    queryParameters.forEach { (key, value) -> parameter(key, value) }
    headers.forEach { (key, value) -> header(key, value) }
}
