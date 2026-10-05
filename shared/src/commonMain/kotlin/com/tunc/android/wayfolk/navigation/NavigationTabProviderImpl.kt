package com.tunc.android.wayfolk.navigation

import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.NavigationTabProvider
import com.tunc.wayfolk.navigation.model.router.ComposeRouter

internal class NavigationTabProviderImpl(
    private val navigationItems: List<BottomNavigationItem>,
    private val startDestination: ComposeRouter
) : NavigationTabProvider {

    override fun getStartDestination(): ComposeRouter = startDestination

    override fun getNavigationTabItems() = navigationItems
        .filter { it.isHidden.not() }
        .sortedBy { it.order }
}
