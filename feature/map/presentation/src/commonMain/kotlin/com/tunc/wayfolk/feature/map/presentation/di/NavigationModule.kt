package com.tunc.wayfolk.feature.map.presentation.di

import com.tunc.wayfolk.feature.map.presentation.navigation.MapNavigationItem
import com.tunc.wayfolk.feature.map.presentation.navigation.MapScreenEntryProvider
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.NavEntryProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val navigationModule = module {
    singleOf(::MapScreenEntryProvider) { bind<NavEntryProvider>() }
    singleOf(::MapNavigationItem) { bind<BottomNavigationItem>() }
}
