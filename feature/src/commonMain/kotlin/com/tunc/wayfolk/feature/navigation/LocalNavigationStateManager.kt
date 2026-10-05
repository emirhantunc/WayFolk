package com.tunc.wayfolk.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.tunc.wayfolk.common.BottomNavigationStateManager


val LocalWayFolkBottomNavigationStateManager: ProvidableCompositionLocal<BottomNavigationStateManager> =
    compositionLocalOf {
        error(
            "No StringResourceManager provided! "
        )
    }

val appBottomNavigationStateManager: BottomNavigationStateManager
    @Composable
    @ReadOnlyComposable
    get() = LocalWayFolkBottomNavigationStateManager.current