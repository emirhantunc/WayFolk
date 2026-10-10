package com.tunc.wayfolk.navigation.model

import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter

sealed interface TabEvent {
    data class OnClickAtRoot(val router: ComposeTabScreenRouter) : TabEvent
}