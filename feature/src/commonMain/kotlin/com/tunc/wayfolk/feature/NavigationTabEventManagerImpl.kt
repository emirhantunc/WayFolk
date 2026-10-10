package com.tunc.wayfolk.feature

import com.tunc.wayfolk.navigation.NavigationTabEventManager
import com.tunc.wayfolk.navigation.model.TabEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class NavigationTabEventManagerImpl : NavigationTabEventManager, CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val _tabEventChannel = Channel<TabEvent>()
    override val tabEventChannel: Flow<TabEvent>
        get() = _tabEventChannel.receiveAsFlow()

    override fun sendEvent(tabEvent: TabEvent) {
        launch {
            _tabEventChannel.send(tabEvent)
        }
    }
}
