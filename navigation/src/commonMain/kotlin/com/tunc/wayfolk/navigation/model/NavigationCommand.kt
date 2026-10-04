package com.tunc.wayfolk.navigation.model

import com.tunc.wayfolk.navigation.model.router.ComposeRouter


sealed interface NavigationCommand {
    data class OpenScreen(
        val screen: ComposeRouter,
        val popUpTo: PopUpToSpec? = null,
        val internPopBackStack: Boolean = false,
        val resetToRoot: Boolean = false,
        val isReplaceScreen: Boolean = false,
        val popupRouter: ComposeRouter? = null,
        val enableDebounce: Boolean = true
    ) : NavigationCommand

    data object NavigateUp : NavigationCommand

    data class PopBackStack(
        val destinationScreen: ComposeRouter,
        val inclusive: Boolean
    ) : NavigationCommand

    data class NavigateBackWithResult<T>(
        val key: String,
        val result: T
    ) : NavigationCommand
}
