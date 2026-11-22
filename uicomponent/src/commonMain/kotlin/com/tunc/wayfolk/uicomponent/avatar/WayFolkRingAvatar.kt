package com.tunc.wayfolk.uicomponent.avatar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.uicomponent.asyncimage.WayFolkAsyncImage

@Immutable
enum class WayFolkAvatarRing { Active, Inactive, None }

@Composable
fun WayFolkRingAvatar(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: Dp = WayFolkRingAvatarDefaults.LargeSize,
    ringWidth: Dp = WayFolkRingAvatarDefaults.LargeRingWidth,
    gapWidth: Dp = WayFolkRingAvatarDefaults.GapWidth,
    ring: WayFolkAvatarRing = WayFolkAvatarRing.Active,
    onLoading: ((AsyncImagePainter.State.Loading) -> Unit)? = null,
    onSuccess: ((AsyncImagePainter.State.Success) -> Unit)? = null,
    onError: ((AsyncImagePainter.State.Error) -> Unit)? = null,
    overlay: @Composable BoxScope.() -> Unit = {},
) {
    val colors = AppThemeProvider.colors
    val ringBrush = when (ring) {
        WayFolkAvatarRing.Active -> Brush.linearGradient(
            colors = listOf(colors.neutral1000.color, colors.neutral600.color, colors.neutral400.color),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f),
        )
        WayFolkAvatarRing.Inactive -> SolidColor(colors.outlineVariant.color)
        WayFolkAvatarRing.None -> SolidColor(Color.Transparent)
    }

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(ringBrush)
            .padding(ringWidth)
            .clip(CircleShape)
            .background(colors.surface.color)
            .padding(gapWidth)
            .clip(CircleShape)
            .background(colors.surfaceContainer.color),
        contentAlignment = Alignment.Center,
    ) {
        WayFolkAsyncImage(
            url = imageUrl,
            contentDescription = contentDescription,
            modifier = Modifier.matchParentSize(),
            onLoading = onLoading,
            onSuccess = onSuccess,
            onError = onError,
        )
        overlay()
    }
}

object WayFolkRingAvatarDefaults {
    val LargeSize: Dp = 64.dp
    val LargeRingWidth: Dp = 2.5.dp
    val SmallSize: Dp = 36.dp
    val SmallRingWidth: Dp = 2.dp
    val GapWidth: Dp = 2.dp
}
