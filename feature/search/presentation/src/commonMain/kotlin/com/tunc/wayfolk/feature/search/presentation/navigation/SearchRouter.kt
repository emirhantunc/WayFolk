package com.tunc.wayfolk.feature.search.presentation.navigation

import com.tunc.wayfolk.navigation.model.router.BaseRouter
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import kotlinx.serialization.Serializable

internal object SearchRouter : BaseRouter {
    @Serializable
    data object Search : ComposeTabScreenRouter {
        override val screenName: String
            get() = "Search"
    }
}
