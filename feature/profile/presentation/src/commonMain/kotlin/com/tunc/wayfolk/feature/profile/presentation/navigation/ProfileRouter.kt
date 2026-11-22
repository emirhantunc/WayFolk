package com.tunc.wayfolk.feature.profile.presentation.navigation

import com.tunc.wayfolk.navigation.model.router.BaseRouter
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import kotlinx.serialization.Serializable

internal object ProfileRouter : BaseRouter {
    @Serializable
    data object Profile : ComposeTabScreenRouter {
        override val screenName: String
            get() = "Profile"
    }
}
