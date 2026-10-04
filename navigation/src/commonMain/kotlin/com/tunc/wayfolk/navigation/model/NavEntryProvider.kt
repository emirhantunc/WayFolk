package com.tunc.wayfolk.navigation.model

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

interface NavEntryProvider {

    fun EntryProviderScope<NavKey>.register()
}
