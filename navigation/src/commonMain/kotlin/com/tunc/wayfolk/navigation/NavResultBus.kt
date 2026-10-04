package com.tunc.wayfolk.navigation

import kotlinx.coroutines.flow.SharedFlow


data class NavResult(val key: String, val value: Any?)

interface NavResultBus {

    val results: SharedFlow<NavResult>

    suspend fun emit(key: String, value: Any?)
}