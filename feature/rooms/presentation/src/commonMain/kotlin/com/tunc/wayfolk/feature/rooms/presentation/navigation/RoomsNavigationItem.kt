package com.tunc.wayfolk.feature.rooms.presentation.navigation

import com.tunc.android.jvm.Empty
import com.tunc.wayfolk.feature.rooms.presentation.util.RoomsConstants.AutomationTag.ROOMS_TAB_ITEM
import com.tunc.wayfolk.multilanguage.language.WayFolkStringResource
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import com.tunc.wayfolk.resources.Res
import com.tunc.wayfolk.resources.ic_rooms
import org.jetbrains.compose.resources.DrawableResource

internal class RoomsNavigationItem : BottomNavigationItem {

    override val title: WayFolkStringResource
        get() = WayFolkStrings.Rooms.tabTitle

    override val icon: DrawableResource
        get() = Res.drawable.ic_rooms

    override val unSelectedIcon: DrawableResource
        get() = Res.drawable.ic_rooms

    override val router: ComposeTabScreenRouter
        get() = RoomsRouter.Rooms

    override val order: Int
        get() = 3

    override val badgeKey: String
        get() = String.Empty

    override val contentDescription: String
        get() = "description"

    override val accessibilityId: String
        get() = ROOMS_TAB_ITEM

    override val isInternPopBackStack: Boolean
        get() = false
}
