package com.tunc.wayfolk.uicomponent.loading.util

import androidx.compose.runtime.Composable

data class PlatformConfiguration(
    val screenWidthDp: Int,
    val screenHeightDp: Int,
) {
    val smallestScreenWidthDp: Int
        get() = minOf(screenWidthDp, screenHeightDp)
}

@Composable
expect fun currentPlatformConfiguration(): PlatformConfiguration
