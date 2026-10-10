package com.tunc.wayfolk.feature.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.tunc.wayfolk.core.presentation.rememberWayFolkLoadingCoreLoadingState
import com.tunc.wayfolk.navigation.LocalWayFolkAnimatedVisibilityScope
import com.tunc.wayfolk.navigation.model.router.ComposeRouter


const val DEFAULT_ANIMATION_DURATION = 350
const val SLIDE_ANIMATION_DURATION = 300

inline fun <reified SCREEN : ComposeRouter> EntryProviderScope<NavKey>.appEntry(
    enter: EnterTransition = slideInHorizontally(animationSpec = tween(SLIDE_ANIMATION_DURATION)) { it },
    exit: ExitTransition = fadeOut(animationSpec = tween(DEFAULT_ANIMATION_DURATION)),
    inheritPopTransitions: Boolean = false,
    popEnter: EnterTransition = fadeIn(animationSpec = tween(DEFAULT_ANIMATION_DURATION)),
    popExit: ExitTransition = slideOutHorizontally(animationSpec = tween(SLIDE_ANIMATION_DURATION)) { it },
    crossinline content: @Composable (SCREEN) -> Unit
) {
    val resolvedPopEnter = if (inheritPopTransitions) enter else popEnter
    val resolvedPopExit = if (inheritPopTransitions) exit else popExit

    appEntry<SCREEN>(
        metadata = NavDisplay.transitionSpec { enter togetherWith exit } +
                NavDisplay.popTransitionSpec { resolvedPopEnter togetherWith resolvedPopExit } +
                NavDisplay.predictivePopTransitionSpec { resolvedPopEnter togetherWith resolvedPopExit },
        content = content
    )
}

/**
 * Lower-level variant that takes pre-built [androidx.navigation3.ui.NavDisplay] [metadata]. Use it when transitions must
 * be decided at transition time (e.g. the product-detail hero animation, which switches between
 * a shared-element fade and the default slide depending on a runtime flag).
 */
inline fun <reified SCREEN : ComposeRouter> EntryProviderScope<NavKey>.appEntry(
    metadata: Map<String, Any>,
    crossinline content: @Composable (SCREEN) -> Unit
) {
    entry<SCREEN>(metadata = metadata) { key ->
        AppEntryContent { content(key) }
    }
}

/**
 * Shared entry content wrapper: exposes the Nav3 [AnimatedContentScope][androidx.navigation3.ui.LocalNavAnimatedContentScope]
 * as [com.tunc.android.navigation.LocalAppAnimatedVisibilityScope] (so screens can run shared-element / hero transitions) and
 * preserves the loading-aware [androidx.activity.compose.BackHandler] guard — while a screen is in a blocking loading state,
 * back is consumed.
 */


@PublishedApi
@Composable
internal fun AppEntryContent(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        value = LocalWayFolkAnimatedVisibilityScope provides LocalNavAnimatedContentScope.current,
        content = @Composable {
            val loadingState = rememberWayFolkLoadingCoreLoadingState()

            NavigationBackHandler(
                state = rememberNavigationEventState(currentInfo = NavigationEventInfo.None),
                isBackEnabled = loadingState.loading,
                onBackCompleted = {}
            )
            content()
        }
    )
}
