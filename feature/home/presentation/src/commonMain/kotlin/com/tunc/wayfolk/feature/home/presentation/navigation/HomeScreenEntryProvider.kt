package com.tunc.wayfolk.feature.home.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.tunc.wayfolk.feature.home.presentation.screen.HomeScreen
import com.tunc.wayfolk.feature.navigation.DEFAULT_ANIMATION_DURATION
import com.tunc.wayfolk.feature.navigation.appEntry
import com.tunc.wayfolk.navigation.model.NavEntryProvider

internal class HomeScreenEntryProvider : NavEntryProvider {
    override fun EntryProviderScope<NavKey>.register() {
        appEntry<HomeRouter.Home>(
            enter = fadeIn(animationSpec = tween(DEFAULT_ANIMATION_DURATION)),
            exit = fadeOut(animationSpec = tween(DEFAULT_ANIMATION_DURATION)),
            inheritPopTransitions = true
        ) {
            HomeScreen()
        }
    }
}