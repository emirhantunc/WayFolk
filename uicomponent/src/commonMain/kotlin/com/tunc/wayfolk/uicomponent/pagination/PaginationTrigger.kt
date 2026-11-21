package com.tunc.wayfolk.uicomponent.pagination

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull

private const val DEFAULT_PREFETCH_DISTANCE = 3

@Composable
fun LazyListState.OnPaginationThreshold(
    enabled: Boolean,
    prefetchDistance: Int = DEFAULT_PREFETCH_DISTANCE,
    onLoadMore: () -> Unit,
) {
    val currentOnLoadMore by rememberUpdatedState(onLoadMore)

    LaunchedEffect(this, enabled, prefetchDistance) {
        if (!enabled) return@LaunchedEffect
        snapshotFlow {
            val layoutInfo = layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            val reachedThreshold = totalItems > 0 && lastVisibleIndex >= totalItems - 1 - prefetchDistance
            totalItems.takeIf { reachedThreshold }
        }
            .filterNotNull()
            .distinctUntilChanged()
            .collect { currentOnLoadMore() }
    }
}
