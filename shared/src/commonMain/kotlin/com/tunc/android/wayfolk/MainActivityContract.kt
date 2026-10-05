package com.tunc.android.wayfolk

import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.router.ComposeRouter
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter

object MainActivityContract {

    data class State(
        val tabItems: List<BottomNavigationItem>,
        val startDestination: ComposeRouter,
        val selectedTabRoute: ComposeTabScreenRouter,
        val isSdksInitialized: Boolean = false,
        val graphState: GraphState = GraphState.StartSplash
    )


    sealed interface Action {
        data class OnSelectTab(val item: BottomNavigationItem, val sendEvent: Boolean) : Action
        data class OnGraphStateChange(val graphState: GraphState) : Action
    }
}