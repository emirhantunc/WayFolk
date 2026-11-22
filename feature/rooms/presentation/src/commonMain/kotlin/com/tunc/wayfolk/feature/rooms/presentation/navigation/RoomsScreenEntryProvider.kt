package com.tunc.wayfolk.feature.rooms.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.tunc.wayfolk.feature.rooms.presentation.screen.RoomsScreen
import com.tunc.wayfolk.feature.navigation.DEFAULT_ANIMATION_DURATION
import com.tunc.wayfolk.feature.navigation.appEntry
import com.tunc.wayfolk.navigation.model.NavEntryProvider

internal class RoomsScreenEntryProvider : NavEntryProvider {
    override fun EntryProviderScope<NavKey>.register() {
        appEntry<RoomsRouter.Rooms>(
            enter = fadeIn(animationSpec = tween(DEFAULT_ANIMATION_DURATION)),
            exit = fadeOut(animationSpec = tween(DEFAULT_ANIMATION_DURATION)),
            inheritPopTransitions = true
        ) {
            RoomsScreen()
        }
    }
}
