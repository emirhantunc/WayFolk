package com.tunc.wayfolk.splash.presentation.navigation

import com.tunc.wayfolk.navigation.model.router.ComposeScreenRouter
import com.tunc.wayfolk.splash.presentation.util.ScreenConstants
import kotlinx.serialization.Serializable

object SplashRouter {
    @Serializable
    data object Splash : ComposeScreenRouter {
        override val screenName: String
            get() = ScreenConstants.Splash.NAME
    }
}