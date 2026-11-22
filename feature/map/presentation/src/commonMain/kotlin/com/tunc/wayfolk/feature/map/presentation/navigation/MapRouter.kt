package com.tunc.wayfolk.feature.map.presentation.navigation

import com.tunc.wayfolk.navigation.model.router.BaseRouter
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import kotlinx.serialization.Serializable

internal object MapRouter : BaseRouter {
    @Serializable
    data object Map : ComposeTabScreenRouter {
        override val screenName: String
            get() = "Map"
    }
}
