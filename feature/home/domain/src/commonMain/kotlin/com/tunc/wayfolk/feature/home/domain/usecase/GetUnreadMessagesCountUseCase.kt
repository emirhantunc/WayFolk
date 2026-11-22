package com.tunc.wayfolk.feature.home.domain.usecase

import com.tunc.wayfolk.contract.network.RestResult
import com.tunc.wayfolk.core.extension.flowOnIO
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetUnreadMessagesCountUseCase(
    private val homeRepository: HomeRepository,
) {
    operator fun invoke(): Flow<RestResult<Int>> =
        homeRepository.getUnreadMessagesCount().flowOnIO()
}
