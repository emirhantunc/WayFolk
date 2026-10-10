package com.tunc.wayfolk.feature.navigation

import com.tunc.wayfolk.navigation.AppComposeNavigator
import com.tunc.wayfolk.navigation.NavResultBus
import com.tunc.wayfolk.navigation.TopLevelBackStack
import com.tunc.wayfolk.navigation.extension.route
import com.tunc.wayfolk.navigation.model.NavigationCommand
import com.tunc.wayfolk.navigation.model.router.ComposeRouter
import com.tunc.wayfolk.navigation.model.router.ComposeScreenRouter
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.TimeMark
import kotlin.time.TimeSource

private val DEBOUNCE_DELAY = 200.milliseconds

internal class AppComposeNavigatorImpl(
    private val navResultBus: NavResultBus
) : AppComposeNavigator, CoroutineScope {

    private var lastSafeNavigateMark: TimeMark? = null
    private var lastSafeNavigateRoute: String? = null

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override suspend fun handleNavigationCommands(
        backStack: TopLevelBackStack,
        navigationCommands: Flow<NavigationCommand>,
    ) {
        navigationCommands.collect { command ->
            backStack.handle(command)
        }
    }

    private fun TopLevelBackStack.handle(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.OpenScreen -> {
                openScreen(command)
            }

            is NavigationCommand.NavigateUp -> {
                onSystemBack()
            }

            is NavigationCommand.PopBackStack -> {
                popUpTo(
                    target = command.destinationScreen,
                    inclusive = command.inclusive
                )
            }

            is NavigationCommand.NavigateBackWithResult<*> -> {
                launch { navResultBus.emit(command.key, command.result) }
                onSystemBack()
            }
        }
    }

    private fun TopLevelBackStack.openScreen(command: NavigationCommand.OpenScreen) {
        val screen = command.screen

        if (command.resetToRoot) {
            setRoot(screen)
            return
        }

        if (screen is ComposeTabScreenRouter) {
            switchTab(
                target = screen,
                clearToRoot = command.internPopBackStack,
                popupRouter = command.popupRouter
            )
            return
        }

        if (screen !is ComposeScreenRouter) return

        if (!canSafeNavigate(screen, command.enableDebounce)) return

        if (command.isReplaceScreen) {
            replaceTop(screen)
        } else {
            command.popUpTo?.let { popUpTo(it.target, it.inclusive) }
            add(screen)
        }
    }

    private fun canSafeNavigate(router: ComposeRouter, enableDebounce: Boolean): Boolean {
        if (!enableDebounce) {
            return true
        }

        val currentRoute = router.route
        val lastMark = lastSafeNavigateMark
        if (
            currentRoute == lastSafeNavigateRoute &&
            lastMark != null &&
            lastMark.elapsedNow() < DEBOUNCE_DELAY
        ) {
            return false
        }

        lastSafeNavigateRoute = currentRoute
        lastSafeNavigateMark = TimeSource.Monotonic.markNow()
        return true
    }
}
