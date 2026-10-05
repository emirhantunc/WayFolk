package com.tunc.wayfolk.navigation.model

import kotlinx.coroutines.flow.Flow

interface ComposeNavManager {

    val navigationFlow: Flow<NavigationCommand>

    fun navigate(navigationCommand: NavigationCommand)
}