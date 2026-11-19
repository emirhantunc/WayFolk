package com.tunc.wayfolk.designsystemkit.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class WayFolkColor(
    val color: Color,
    val token: String? = null
)

fun WayFolkColor.byAlpha(alpha: Float) = WayFolkColor(color.copy(alpha = alpha), token)
