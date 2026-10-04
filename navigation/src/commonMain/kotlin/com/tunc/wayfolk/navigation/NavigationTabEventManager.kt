package com.tunc.wayfolk.navigation

import com.tunc.wayfolk.navigation.model.TabEvent
import kotlinx.coroutines.flow.Flow

interface NavigationTabEventManager {
    val tabEventChannel: Flow<TabEvent>
    fun sendEvent(tabEvent: TabEvent)
}