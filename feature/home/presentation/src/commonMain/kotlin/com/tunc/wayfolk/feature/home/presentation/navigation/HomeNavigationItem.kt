package com.tunc.wayfolk.feature.home.presentation.navigation

import com.tunc.android.jvm.Empty
import com.tunc.wayfolk.feature.home.presentation.util.HomeConstants.AutomationTag.HOME_TAB_ITEM
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import com.tunc.wayfolk.resources.Res
import com.tunc.wayfolk.resources.home_tab_title
import com.tunc.wayfolk.resources.ic_home_filled
import com.tunc.wayfolk.resources.ic_home_outlined
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

internal class HomeNavigationItem : BottomNavigationItem {

    override val title: StringResource
        get() = Res.string.home_tab_title

    override val icon: DrawableResource
        get() = Res.drawable.ic_home_filled

    override val unSelectedIcon: DrawableResource
        get() = Res.drawable.ic_home_outlined

    override val router: ComposeTabScreenRouter
        get() = HomeRouter.Home

    override val order: Int
        get() = 1

    override val badgeKey: String
        get() = String.Empty

    override val contentDescription: String
        get() = "description"

    override val accessibilityId: String
        get() = HOME_TAB_ITEM

    override val isInternPopBackStack: Boolean
        get() = false
}
