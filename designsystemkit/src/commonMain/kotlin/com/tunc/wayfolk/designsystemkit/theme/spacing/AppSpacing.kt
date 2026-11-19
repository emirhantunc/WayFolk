package com.tunc.wayfolk.designsystemkit.theme.spacing

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

object AppSpacing {
    val space0 = 0.dp
    val space025 = 2.dp
    val space050 = 4.dp
    val space075 = 6.dp
    val space100 = 8.dp
    val space125 = 10.dp
    val space150 = 12.dp
    val space175 = 14.dp
    val space200 = 16.dp
    val space225 = 18.dp
    val space250 = 20.dp
    val space300 = 24.dp
    val space350 = 28.dp
    val space600 = 48.dp
    val space900 = 72.dp
}

internal val AppLocalSpacing = staticCompositionLocalOf { AppSpacing }
