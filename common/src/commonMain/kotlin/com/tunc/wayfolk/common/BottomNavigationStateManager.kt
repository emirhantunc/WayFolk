package com.tunc.wayfolk.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BottomNavigationStateManager : CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val stateChannel = Channel<BottomNavigationState>()
    val stateFlow = stateChannel.receiveAsFlow()

    private val effectChannel = Channel<GraphState>()
    val effectFlow = effectChannel.receiveAsFlow()

    fun updateState(state: BottomNavigationState) {
        launch {
            stateChannel.send(state)
        }
    }

    fun updateEffect(state: GraphState) {
        launch {
            effectChannel.send(state)
        }
    }
}