package com.tunc.wayfolk.core.presentation.util

import androidx.compose.runtime.Composable

expect open class PlatformActivity

expect val currentPlatformActivity: PlatformActivity?
    @Composable get

expect fun PlatformActivity?.showStatusBar()

expect fun PlatformActivity?.hideStatusBar()
