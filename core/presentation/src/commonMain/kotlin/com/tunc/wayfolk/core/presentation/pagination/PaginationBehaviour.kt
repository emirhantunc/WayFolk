package com.tunc.wayfolk.core.presentation.pagination

import com.tunc.wayfolk.contract.network.RestResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface PaginationBehaviour<Key : Any, Page : Any> {

    val paginationState: PaginationState

    fun setupPagination(
        scope: CoroutineScope,
        initialKey: Key? = null,
        request: (key: Key?, isFirstPage: Boolean) -> Flow<RestResult<Page>>,
        nextKey: (Page) -> Key?,
        onPageLoaded: (page: Page, isFirstPage: Boolean) -> Unit,
        onStateChange: (PaginationState) -> Unit,
    )

    fun loadNextPage()

    fun refreshPagination()
}
