package com.tunc.wayfolk.feature.home.domain.usecase

import com.tunc.wayfolk.contract.network.RestResult
import com.tunc.wayfolk.core.extension.flowOnIO
import com.tunc.wayfolk.feature.home.domain.model.HomeFeed
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetHomeFeedUseCase(
    private val homeRepository: HomeRepository,
) {
    operator fun invoke(cursor: String?, pageSize: Int): Flow<RestResult<HomeFeed>> =
        homeRepository.getFeed(cursor = cursor, pageSize = pageSize).flowOnIO()
}
