package com.tunc.wayfolk.core.presentation.util

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable

actual val WindowInsets.Companion.statusBarsIgnoringVisibilityCompat: WindowInsets
    @Composable get() = WindowInsets.statusBars
