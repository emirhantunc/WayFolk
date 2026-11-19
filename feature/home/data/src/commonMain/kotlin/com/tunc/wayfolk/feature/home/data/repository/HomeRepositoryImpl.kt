package com.tunc.wayfolk.feature.home.data.repository

import com.tunc.wayfolk.contract.network.RestResult
import com.tunc.wayfolk.core.network.ApiHandler
import com.tunc.wayfolk.feature.home.data.mapper.toDomain
import com.tunc.wayfolk.feature.home.data.remote.HomeApi
import com.tunc.wayfolk.feature.home.domain.model.HomeFeed
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

internal class HomeRepositoryImpl(
    private val homeApi: HomeApi,
    private val apiHandler: ApiHandler,
) : HomeRepository {

    override fun getFeed(): Flow<RestResult<HomeFeed>> =
        apiHandler.safeApiCall { homeApi.getFeed().toDomain() }
}
