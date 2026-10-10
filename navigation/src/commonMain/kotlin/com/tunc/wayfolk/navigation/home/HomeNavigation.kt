package com.tunc.wayfolk.navigation.home

import com.tunc.wayfolk.navigation.model.router.ComposeRouter

interface HomeNavigation {
    fun navigateToHome(clearBackStack: Boolean = false, popUpRoute: ComposeRouter? = null)
}