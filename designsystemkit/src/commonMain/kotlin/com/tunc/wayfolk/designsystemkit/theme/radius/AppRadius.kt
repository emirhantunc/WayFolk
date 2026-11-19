package com.tunc.wayfolk.designsystemkit.theme.radius

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

object AppRadius {
    val radius0 = 0.dp
    val radius2 = 2.dp
    val radius4 = 4.dp
    val radius10 = 10.dp
    val radius12 = 12.dp
    val radius14 = 14.dp
    val radius16 = 16.dp
    val radius24 = 24.dp
    val radiusFull = 9999.dp
}

internal val AppLocalRadius = staticCompositionLocalOf { AppRadius }
