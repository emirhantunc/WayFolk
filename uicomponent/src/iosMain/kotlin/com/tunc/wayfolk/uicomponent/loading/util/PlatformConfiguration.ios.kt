package com.tunc.wayfolk.uicomponent.loading.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo

@Composable
actual fun currentPlatformConfiguration(): PlatformConfiguration {
    val containerSize = LocalWindowInfo.current.containerSize
    val density = LocalDensity.current

    return remember(containerSize, density) {
        with(density) {
            PlatformConfiguration(
                screenWidthDp = containerSize.width.toDp().value.toInt(),
                screenHeightDp = containerSize.height.toDp().value.toInt(),
            )
        }
    }
}
