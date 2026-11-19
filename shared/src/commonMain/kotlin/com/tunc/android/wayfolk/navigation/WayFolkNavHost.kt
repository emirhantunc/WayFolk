package com.tunc.android.wayfolk.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.tunc.wayfolk.multilanguage.language.AppLanguage
import com.tunc.wayfolk.multilanguage.language.LanguageManager
import com.tunc.wayfolk.multilanguage.language.LocalAppLanguage
import com.tunc.wayfolk.multilanguage.language.LocalAppLocale
import com.tunc.wayfolk.navigation.TopLevelBackStack
import com.tunc.wayfolk.navigation.model.NavEntryProvider
import org.koin.compose.getKoin
import org.koin.compose.koinInject
import kotlin.collections.distinct


@Composable
fun WayFolkNavHost(
    topLevelBackStack: TopLevelBackStack,
    languageManager: LanguageManager = koinInject(),
) {
    val koin = getKoin()
    val language by languageManager.language.collectAsStateWithLifecycle()
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

    val entryDecorators = listOf(
        rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
        rememberViewModelStoreNavEntryDecorator<NavKey>()
    )

    SharedTransitionLayout {
        WayFolkLocalizationProvider(language = language) {
            NavDisplay(
                backStack = topLevelBackStack.backStack,
                onBack = { topLevelBackStack.onSystemBack() },
                entryDecorators = entryDecorators,
                entryProvider = entryProvider
            )
        }
    }
}

@Composable
private fun WayFolkLocalizationProvider(
    language: AppLanguage,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalAppLocale provides language.tag,
        LocalAppLanguage provides language,
    ) {
        key(language) {
            content()
        }
    }
}
