package com.tunc.wayfolk.feature

import com.tunc.wayfolk.navigation.NavResult
import com.tunc.wayfolk.navigation.NavResultBus
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class NavResultBusImpl : NavResultBus {

    private val _results = MutableSharedFlow<NavResult>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val results: SharedFlow<NavResult> = _results.asSharedFlow()

    override suspend fun emit(key: String, value: Any?) {
        _results.emit(NavResult(key, value))
    }
}
