package com.tunc.wayfolk.feature.rooms.presentation.navigation

import com.tunc.wayfolk.navigation.model.router.BaseRouter
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import kotlinx.serialization.Serializable

internal object RoomsRouter : BaseRouter {
    @Serializable
    data object Rooms : ComposeTabScreenRouter {
        override val screenName: String
            get() = "Rooms"
    }
}
