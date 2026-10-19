package com.tunc.wayfolk.uicomponent.loading.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

@Composable
actual fun currentPlatformConfiguration(): PlatformConfiguration {
    val configuration = LocalConfiguration.current

    return remember(configuration.screenWidthDp, configuration.screenHeightDp) {
        PlatformConfiguration(
            screenWidthDp = configuration.screenWidthDp,
            screenHeightDp = configuration.screenHeightDp,
        )
    }
}
