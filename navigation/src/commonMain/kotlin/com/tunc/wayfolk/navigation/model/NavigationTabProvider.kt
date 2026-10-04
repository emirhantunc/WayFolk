package com.tunc.wayfolk.navigation.model

import com.tunc.wayfolk.navigation.model.router.ComposeRouter

interface NavigationTabProvider {
    fun getStartDestination(): ComposeRouter
    fun getNavigationTabItems(): List<BottomNavigationItem>
}