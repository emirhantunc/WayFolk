package com.tunc.wayfolk.uicomponent.loading

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.tunc.wayfolk.uicomponent.skeleton.WayFolkSkeleton
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun AppScreenSkeleton(
    showSkeleton: Boolean,
    skeletonResourceId: DrawableResource,
    contentScale: ContentScale = ContentScale.Crop,
    shimmerStatusDuration: Int? = null,
    skeletonOverlayContent: @Composable ((Boolean) -> Unit)? = null,
    content: @Composable () -> Unit
) {
    WayFolkSkeleton(
        modifier = Modifier.fillMaxSize(),
        showSkeleton = showSkeleton,
        skeletonResourceId = skeletonResourceId,
        shimmerStatusDuration = shimmerStatusDuration,
        contentScale = contentScale,
        skeletonOverlayContent = skeletonOverlayContent,
        content = content
    )
}