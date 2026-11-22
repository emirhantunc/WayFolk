package com.tunc.wayfolk.feature.map.presentation.navigation

import com.tunc.android.jvm.Empty
import com.tunc.wayfolk.feature.map.presentation.util.MapConstants.AutomationTag.MAP_TAB_ITEM
import com.tunc.wayfolk.multilanguage.language.WayFolkStringResource
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import com.tunc.wayfolk.resources.Res
import com.tunc.wayfolk.resources.ic_map
import org.jetbrains.compose.resources.DrawableResource

internal class MapNavigationItem : BottomNavigationItem {

    override val title: WayFolkStringResource
        get() = WayFolkStrings.Map.tabTitle

    override val icon: DrawableResource
        get() = Res.drawable.ic_map

    override val unSelectedIcon: DrawableResource
        get() = Res.drawable.ic_map

    override val router: ComposeTabScreenRouter
        get() = MapRouter.Map

    override val order: Int
        get() = 2

    override val badgeKey: String
        get() = String.Empty

    override val contentDescription: String
        get() = "description"

    override val accessibilityId: String
        get() = MAP_TAB_ITEM

    override val isInternPopBackStack: Boolean
        get() = false
}
