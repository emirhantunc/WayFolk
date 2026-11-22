package com.tunc.wayfolk.feature.home.data.remote

import com.tunc.wayfolk.feature.home.data.remote.dto.HomeFeedResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class HomeApi(
    private val client: HttpClient,
) {
    suspend fun getFeed(cursor: String?, pageSize: Int): HomeFeedResponse =
        client.get(HomeEndpoints.FEED) {
            parameter(HomeEndpoints.PARAM_CURSOR, cursor)
            parameter(HomeEndpoints.PARAM_LIMIT, pageSize)
        }.body()
}
