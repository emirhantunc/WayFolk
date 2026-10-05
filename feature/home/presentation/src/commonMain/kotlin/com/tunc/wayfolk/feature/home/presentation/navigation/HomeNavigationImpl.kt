package com.tunc.wayfolk.feature.home.presentation.navigation

import com.tunc.wayfolk.navigation.home.HomeNavigation
import com.tunc.wayfolk.navigation.model.ComposeNavManager
import com.tunc.wayfolk.navigation.model.NavigationCommand
import com.tunc.wayfolk.navigation.model.router.ComposeRouter

internal class HomeNavigationImpl(
    private val navigationManager: ComposeNavManager
) : HomeNavigation {
    override fun navigateToHome(clearBackStack: Boolean, popUpRoute: ComposeRouter?) {
        navigationManager.navigate(
            NavigationCommand.OpenScreen(
                screen = HomeRouter.Home,
                resetToRoot = clearBackStack,
                popupRouter = popUpRoute
            )
        )
    }
}
