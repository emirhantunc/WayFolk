package com.tunc.wayfolk.feature.profile.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.tunc.wayfolk.feature.profile.presentation.screen.ProfileScreen
import com.tunc.wayfolk.feature.navigation.DEFAULT_ANIMATION_DURATION
import com.tunc.wayfolk.feature.navigation.appEntry
import com.tunc.wayfolk.navigation.model.NavEntryProvider

internal class ProfileScreenEntryProvider : NavEntryProvider {
    override fun EntryProviderScope<NavKey>.register() {
        appEntry<ProfileRouter.Profile>(
            enter = fadeIn(animationSpec = tween(DEFAULT_ANIMATION_DURATION)),
            exit = fadeOut(animationSpec = tween(DEFAULT_ANIMATION_DURATION)),
            inheritPopTransitions = true
        ) {
            ProfileScreen()
        }
    }
}
