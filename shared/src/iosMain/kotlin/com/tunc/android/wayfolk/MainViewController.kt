package com.tunc.android.wayfolk

import androidx.compose.ui.window.ComposeUIViewController
import com.tunc.wayfolk.core.presentation.util.StatusBarController

fun MainViewController() = ComposeUIViewController { App() }

fun setStatusBarVisibilityListener(listener: (Boolean) -> Unit) {
    StatusBarController.setListener(listener)
}
