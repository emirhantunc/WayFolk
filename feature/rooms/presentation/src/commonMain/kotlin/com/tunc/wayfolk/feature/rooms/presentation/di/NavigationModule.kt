package com.tunc.wayfolk.feature.rooms.presentation.di

import com.tunc.wayfolk.feature.rooms.presentation.navigation.RoomsNavigationItem
import com.tunc.wayfolk.feature.rooms.presentation.navigation.RoomsScreenEntryProvider
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.NavEntryProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val navigationModule = module {
    singleOf(::RoomsScreenEntryProvider) { bind<NavEntryProvider>() }
    singleOf(::RoomsNavigationItem) { bind<BottomNavigationItem>() }
}
