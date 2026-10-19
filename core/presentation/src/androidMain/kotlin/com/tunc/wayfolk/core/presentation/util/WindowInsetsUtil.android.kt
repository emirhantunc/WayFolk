package com.tunc.wayfolk.core.presentation.util

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.runtime.Composable

@OptIn(ExperimentalLayoutApi::class)
actual val WindowInsets.Companion.statusBarsIgnoringVisibilityCompat: WindowInsets
    @Composable get() = WindowInsets.statusBarsIgnoringVisibility
