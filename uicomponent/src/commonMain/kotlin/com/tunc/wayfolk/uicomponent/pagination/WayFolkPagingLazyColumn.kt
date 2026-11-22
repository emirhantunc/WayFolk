package com.tunc.wayfolk.uicomponent.pagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

private const val PAGINATION_FOOTER_KEY = "wayfolk_pagination_footer"
private const val PAGINATION_FOOTER_CONTENT_TYPE = "wayfolk_pagination_footer"
private const val DEFAULT_PREFETCH_DISTANCE = 3

@Composable
fun WayFolkPagingLazyColumn(
    isLoadingMore: Boolean,
    canLoadMore: Boolean,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    hasLoadMoreError: Boolean = false,
    onRetryLoadMore: () -> Unit = onLoadMore,
    prefetchDistance: Int = DEFAULT_PREFETCH_DISTANCE,
    contentPadding: PaddingValues = PaddingValues(),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    userScrollEnabled: Boolean = true,
    content: LazyListScope.() -> Unit,
) {
    state.OnPaginationThreshold(
        enabled = canLoadMore && !hasLoadMoreError,
        prefetchDistance = prefetchDistance,
        onLoadMore = onLoadMore,
    )

    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        userScrollEnabled = userScrollEnabled,
    ) {
        content()

        if (isLoadingMore || hasLoadMoreError) {
            item(key = PAGINATION_FOOTER_KEY, contentType = PAGINATION_FOOTER_CONTENT_TYPE) {
                WayFolkPaginationFooter(
                    isLoading = isLoadingMore,
                    hasError = hasLoadMoreError,
                    onRetry = onRetryLoadMore,
                )
            }
        }
    }
}
