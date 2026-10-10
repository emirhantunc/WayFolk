package com.tunc.android.wayfolk.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.tunc.wayfolk.navigation.TopLevelBackStack
import com.tunc.wayfolk.navigation.model.NavEntryProvider
import org.koin.compose.getKoin
import kotlin.collections.distinct


@Composable
fun WayFolkNavHost(topLevelBackStack: TopLevelBackStack) {
    val koin = getKoin()
    val providers = remember { koin.getAll<NavEntryProvider>().distinct() }



    val entryProvider = remember(providers) {
        entryProvider {
            providers.forEach { provider ->
                with(provider) { register() }
            }
        }
    }

    NavigationBackHandler(
        state = rememberNavigationEventState(currentInfo = NavigationEventInfo.None),
        isBackEnabled = topLevelBackStack.backStack.size <= 1,
        onBackCompleted = { topLevelBackStack.onSystemBack() }
    )

    SharedTransitionLayout {
        NavDisplay(
            backStack = topLevelBackStack.backStack,
            onBack = { topLevelBackStack.onSystemBack() },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider
        )
    }

}