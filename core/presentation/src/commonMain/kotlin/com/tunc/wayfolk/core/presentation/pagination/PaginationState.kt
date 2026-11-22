package com.tunc.wayfolk.core.presentation.pagination

import androidx.compose.runtime.Immutable

@Immutable
data class PaginationState(
    val isLoading: Boolean = false,
    val isFirstPage: Boolean = true,
    val hasError: Boolean = false,
    val endReached: Boolean = false,
) {
    val isLoadingNextPage: Boolean
        get() = isLoading && !isFirstPage

    val hasNextPageError: Boolean
        get() = hasError && !isFirstPage
}
