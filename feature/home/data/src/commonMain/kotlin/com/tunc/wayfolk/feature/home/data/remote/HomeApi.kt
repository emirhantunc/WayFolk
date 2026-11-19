package com.tunc.wayfolk.feature.home.data.remote

import com.tunc.wayfolk.feature.home.data.remote.dto.HomeFeedResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class HomeApi(
    private val client: HttpClient,
) {
    suspend fun getFeed(): HomeFeedResponse = client.get(HomeEndpoints.FEED).body()
}
