package com.tunc.wayfolk.uicomponent.skeleton

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import com.tunc.android.jvm.orZero
import com.tunc.wayfolk.designsystemkit.extension.conditionModifier
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.uicomponent.loading.util.rememberDeviceType
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

private const val SHIMMER = "Shimmer"
private const val BRUSH_OFFSET = 500f
private const val SHIMMER_DURATION = 1500
private const val ANIMATED_LABEL = "AppSkeleton"


@Composable
fun WayFolkSkeleton(
    modifier: Modifier = Modifier,
    mainModifier: Modifier = Modifier.fillMaxSize(),
    contentScale: ContentScale,
    showSkeleton: Boolean,
    shimmerStatusDuration: Int? = null,
    skeletonResourceId: DrawableResource,
    skeletonOverlayContent: @Composable ((Boolean) -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val deviceType = rememberDeviceType()
    val scrollState = rememberScrollState()

    val skeletonContentModifier = modifier.conditionModifier(condition = deviceType.isTablet) {
        Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    }

    Box(modifier = mainModifier) {
        AnimatedContent(
            targetState = showSkeleton,
            transitionSpec = {
                fadeIn(animationSpec = tween(shimmerStatusDuration.orZero)).togetherWith(
                    fadeOut(
                        animationSpec = tween(shimmerStatusDuration.orZero)
                    )
                )
            },
            label = ANIMATED_LABEL
        ) { show ->
            if (show) {
                val shimmerColors = listOf(
                    AppThemeProvider.colors.neutral40.color.copy(alpha = 0.4f),
                    AppThemeProvider.colors.neutral0.color.copy(alpha = 0.9f),
                    AppThemeProvider.colors.neutral40.color.copy(alpha = 0.4f)
                )

                val transition = rememberInfiniteTransition(label =SHIMMER)
                val translateAnim by transition.animateFloat(
                    initialValue = 0f,
                    targetValue = 2000f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = SHIMMER_DURATION, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    ),
                    label = SHIMMER
                )

                val brush = Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(translateAnim - BRUSH_OFFSET, translateAnim - BRUSH_OFFSET),
                    end = Offset(translateAnim, translateAnim)
                )

                Box(
                    modifier = skeletonContentModifier
                        .drawWithContent {
                            drawContent()
                            drawRect(
                                brush = brush,
                                blendMode = BlendMode.Screen
                            )
                        }
                ) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(skeletonResourceId),
                        contentDescription = null,
                        contentScale = contentScale
                    )
                }
            } else {
                content()
            }
        }

        skeletonOverlayContent?.invoke(showSkeleton)
    }
}