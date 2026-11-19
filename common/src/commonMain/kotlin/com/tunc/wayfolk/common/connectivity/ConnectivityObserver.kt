package com.tunc.wayfolk.common.connectivity

import kotlinx.coroutines.flow.StateFlow

interface ConnectivityObserver {
    val isConnected: StateFlow<Boolean>
}
