package com.tunc.wayfolk.feature.search.presentation.navigation

import com.tunc.android.jvm.Empty
import com.tunc.wayfolk.feature.search.presentation.util.SearchConstants.AutomationTag.SEARCH_TAB_ITEM
import com.tunc.wayfolk.multilanguage.language.WayFolkStringResource
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import com.tunc.wayfolk.resources.Res
import com.tunc.wayfolk.resources.ic_search
import org.jetbrains.compose.resources.DrawableResource

internal class SearchNavigationItem : BottomNavigationItem {

    override val title: WayFolkStringResource
        get() = WayFolkStrings.Search.tabTitle

    override val icon: DrawableResource
        get() = Res.drawable.ic_search

    override val unSelectedIcon: DrawableResource
        get() = Res.drawable.ic_search

    override val router: ComposeTabScreenRouter
        get() = SearchRouter.Search

    override val order: Int
        get() = 4

    override val badgeKey: String
        get() = String.Empty

    override val contentDescription: String
        get() = "description"

    override val accessibilityId: String
        get() = SEARCH_TAB_ITEM

    override val isInternPopBackStack: Boolean
        get() = false
}
