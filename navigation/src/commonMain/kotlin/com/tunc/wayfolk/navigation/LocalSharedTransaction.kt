package com.tunc.wayfolk.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.compositionLocalOf


val LocalWayFolkSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

val LocalWayFolkAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }