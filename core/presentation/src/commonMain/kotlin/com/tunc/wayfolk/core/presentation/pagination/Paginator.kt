package com.tunc.wayfolk.core.presentation.pagination

import com.tunc.wayfolk.contract.network.RestResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

class Paginator<Key : Any, Page : Any>(
    private val scope: CoroutineScope,
    private val initialKey: Key?,
    private val request: (key: Key?, isFirstPage: Boolean) -> Flow<RestResult<Page>>,
    private val nextKey: (Page) -> Key?,
    private val onPageLoaded: (page: Page, isFirstPage: Boolean) -> Unit,
    private val onStateChange: (PaginationState) -> Unit,
) {

    private var currentKey: Key? = initialKey
    private var isFirstPage: Boolean = true
    private var job: Job? = null

    var state: PaginationState = PaginationState()
        private set(value) {
            if (field == value) return
            field = value
            onStateChange(value)
        }

    fun loadNextPage() {
        if (job?.isActive == true || state.endReached) return
        load()
    }

    fun refresh() {
        job?.cancel()
        currentKey = initialKey
        isFirstPage = true
        state = PaginationState()
        load()
    }

    private fun load() {
        val key = currentKey
        val firstPage = isFirstPage
        state = state.copy(isLoading = true, isFirstPage = firstPage, hasError = false)

        job = request(key, firstPage)
            .onEach { result ->
                when (result) {
                    is RestResult.Success -> onSuccess(result.result, firstPage)
                    is RestResult.SuccessWithStatus -> onSuccess(result.result, firstPage)
                    is RestResult.Empty -> state = state.copy(isLoading = false, endReached = true)
                    is RestResult.Error -> state = state.copy(isLoading = false, hasError = true)
                    is RestResult.Loading -> Unit
                }
            }
            .onCompletion { cause ->
                if (cause == null && state.isLoading) {
                    state = state.copy(isLoading = false, hasError = true)
                }
            }
            .launchIn(scope)
    }

    private fun onSuccess(page: Page, firstPage: Boolean) {
        currentKey = nextKey(page)
        isFirstPage = false
        onPageLoaded(page, firstPage)
        state = state.copy(
            isLoading = false,
            hasError = false,
            endReached = currentKey == null,
        )
    }
}
