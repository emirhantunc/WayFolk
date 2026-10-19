package com.tunc.wayfolk.core.presentation.util

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable

expect val WindowInsets.Companion.statusBarsIgnoringVisibilityCompat: WindowInsets
    @Composable get
