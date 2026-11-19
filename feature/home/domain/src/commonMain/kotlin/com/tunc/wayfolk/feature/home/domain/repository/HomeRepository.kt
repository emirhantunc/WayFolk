package com.tunc.wayfolk.feature.home.domain.repository

import com.tunc.wayfolk.contract.network.RestResult
import com.tunc.wayfolk.feature.home.domain.model.HomeFeed
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getFeed(): Flow<RestResult<HomeFeed>>
}
