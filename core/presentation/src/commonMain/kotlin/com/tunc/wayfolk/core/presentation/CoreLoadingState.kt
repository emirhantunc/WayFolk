package com.tunc.wayfolk.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class WayfolkCoreLoadingState {
    var loading by mutableStateOf(false)
        private set

    fun setLoadingState(loading: Boolean) {
        this.loading = loading
    }
}

private val loadingState = WayfolkCoreLoadingState()

@Composable
fun rememberWayFolkLoadingCoreLoadingState() : WayfolkCoreLoadingState =  remember { loadingState }
