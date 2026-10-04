package com.tunc.wayfolk.navigation.model

import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Suppress("ComplexInterface")
interface BottomNavigationItem {
    val title: StringResource
    val router: ComposeTabScreenRouter
    val order: Int
    val badgeKey: String

    val icon: DrawableResource

    val unSelectedIcon: DrawableResource

    val iconLottiePath: String?
        get() = null

    val unSelectedIconLottiePath: String?
        get() = null

    val contentDescription: String

    val accessibilityId: String

    val isInternPopBackStack: Boolean

    val isHidden: Boolean
        get() = false
}
