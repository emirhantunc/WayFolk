package com.tunc.wayfolk.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.uikit.LocalUIViewController
import platform.UIKit.UIViewController

actual typealias PlatformActivity = UIViewController

actual val currentPlatformActivity: PlatformActivity?
    @Composable get() = LocalUIViewController.current

actual fun PlatformActivity?.showStatusBar() {
    StatusBarController.update(true)
}

actual fun PlatformActivity?.hideStatusBar() {
    StatusBarController.update(false)
}
