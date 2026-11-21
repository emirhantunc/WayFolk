package com.tunc.wayfolk.core.presentation.pagination

import com.tunc.wayfolk.contract.network.RestResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class PaginationBehaviourDelegate<Key : Any, Page : Any> : PaginationBehaviour<Key, Page> {

    private var paginator: Paginator<Key, Page>? = null

    override val paginationState: PaginationState
        get() = requirePaginator().state

    override fun setupPagination(
        scope: CoroutineScope,
        initialKey: Key?,
        request: (key: Key?, isFirstPage: Boolean) -> Flow<RestResult<Page>>,
        nextKey: (Page) -> Key?,
        onPageLoaded: (page: Page, isFirstPage: Boolean) -> Unit,
        onStateChange: (PaginationState) -> Unit,
    ) {
        check(paginator == null) { "setupPagination must be called only once" }
        paginator = Paginator(
            scope = scope,
            initialKey = initialKey,
            request = request,
            nextKey = nextKey,
            onPageLoaded = onPageLoaded,
            onStateChange = onStateChange,
        )
    }

    override fun loadNextPage() {
        requirePaginator().loadNextPage()
    }

    override fun refreshPagination() {
        requirePaginator().refresh()
    }

    private fun requirePaginator(): Paginator<Key, Page> =
        checkNotNull(paginator) { "setupPagination must be called before using pagination" }
}
