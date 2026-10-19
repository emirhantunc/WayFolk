package com.tunc.wayfolk.core.presentation.util

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

actual typealias PlatformActivity = Activity

actual val currentPlatformActivity: PlatformActivity?
    @Composable get() = LocalActivity.current

actual fun PlatformActivity?.showStatusBar() {
    this?.updateStatusBarDisplay(true)
}

actual fun PlatformActivity?.hideStatusBar() {
    this?.updateStatusBarDisplay(false)
}

private fun Activity.updateStatusBarDisplay(show: Boolean) {
    WindowInsetsControllerCompat(window, window.decorView).apply {
        val type = WindowInsetsCompat.Type.statusBars()

        if (show) {
            show(type)
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
        } else {
            hide(type)
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}
