package com.tunc.wayfolk.feature.home.data.repository

import com.tunc.wayfolk.contract.network.RestResult
import com.tunc.wayfolk.core.network.ApiHandler
import com.tunc.wayfolk.core.network.sendRequest
import com.tunc.wayfolk.feature.home.data.constant.HomeEndpoints
import com.tunc.wayfolk.feature.home.data.mapper.toDomain
import com.tunc.wayfolk.feature.home.data.model.HomeFeedResponse
import com.tunc.wayfolk.feature.home.domain.model.HomeFeed
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

internal class HomeRepositoryImpl(
    private val httpClient: HttpClient,
    private val apiHandler: ApiHandler,
) : HomeRepository {

    override fun getFeed(cursor: String?, pageSize: Int): Flow<RestResult<HomeFeed>> =
        apiHandler.safeApiCall {
            httpClient.sendRequest<HomeFeedResponse>(
                endpoint = HomeEndpoints.FEED,
                queryParameters = mapOf(
                    HomeEndpoints.QueryParam.CURSOR to cursor,
                    HomeEndpoints.QueryParam.LIMIT to pageSize,
                ),
            ).toDomain()
        }
}
