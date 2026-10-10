package com.tunc.wayfolk.navigation

import com.tunc.wayfolk.navigation.model.NavigationCommand
import kotlinx.coroutines.flow.Flow


interface AppComposeNavigator {
    suspend fun handleNavigationCommands(
        backStack: TopLevelBackStack,
        navigationCommands: Flow<NavigationCommand>,
    )
}

