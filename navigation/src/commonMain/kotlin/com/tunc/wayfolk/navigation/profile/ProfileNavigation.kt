package com.tunc.wayfolk.navigation.profile

import com.tunc.wayfolk.navigation.model.router.ComposeRouter

interface ProfileNavigation {
    fun navigateToProfile(
        clearBackStack: Boolean = false,
        popUpRoute: ComposeRouter? = null,
        id: String? = null
    )
}