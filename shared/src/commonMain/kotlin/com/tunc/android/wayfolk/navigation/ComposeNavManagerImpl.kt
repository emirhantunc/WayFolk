package com.tunc.android.wayfolk.navigation

import com.tunc.wayfolk.navigation.model.ComposeNavManager
import com.tunc.wayfolk.navigation.model.NavigationCommand
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class ComposeNavManagerImpl(
    dispatcher: CoroutineDispatcher
) : CoroutineScope, ComposeNavManager {

    override val coroutineContext: CoroutineContext = dispatcher + SupervisorJob()

    private val navigationChannel = Channel<NavigationCommand>()
    override val navigationFlow: Flow<NavigationCommand>
        get() = navigationChannel.receiveAsFlow()

    override fun navigate(navigationCommand: NavigationCommand) {
        launch {
            navigationChannel.send(navigationCommand)
        }
    }
}
