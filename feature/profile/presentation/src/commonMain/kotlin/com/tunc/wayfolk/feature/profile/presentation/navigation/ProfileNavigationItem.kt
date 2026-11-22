package com.tunc.wayfolk.feature.profile.presentation.navigation

import com.tunc.android.jvm.Empty
import com.tunc.wayfolk.feature.profile.presentation.util.ProfileConstants.AutomationTag.PROFILE_TAB_ITEM
import com.tunc.wayfolk.multilanguage.language.WayFolkStringResource
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import com.tunc.wayfolk.resources.Res
import com.tunc.wayfolk.resources.ic_profile
import org.jetbrains.compose.resources.DrawableResource

internal class ProfileNavigationItem : BottomNavigationItem {

    override val title: WayFolkStringResource
        get() = WayFolkStrings.Profile.tabTitle

    override val icon: DrawableResource
        get() = Res.drawable.ic_profile

    override val unSelectedIcon: DrawableResource
        get() = Res.drawable.ic_profile

    override val router: ComposeTabScreenRouter
        get() = ProfileRouter.Profile

    override val order: Int
        get() = 5

    override val badgeKey: String
        get() = String.Empty

    override val contentDescription: String
        get() = "description"

    override val accessibilityId: String
        get() = PROFILE_TAB_ITEM

    override val isInternPopBackStack: Boolean
        get() = false
}
