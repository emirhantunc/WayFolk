package com.tunc.wayfolk.feature.home.presentation.navigation

import com.tunc.wayfolk.navigation.model.router.BaseRouter
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import kotlinx.serialization.Serializable

internal object HomeRouter : BaseRouter {
    @Serializable
    data object Home : ComposeTabScreenRouter {
        override val screenName: String
            get() = "Home"
    }
}
