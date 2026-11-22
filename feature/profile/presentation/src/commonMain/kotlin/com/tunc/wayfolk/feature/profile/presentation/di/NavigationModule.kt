package com.tunc.wayfolk.feature.profile.presentation.di

import com.tunc.wayfolk.feature.profile.presentation.navigation.ProfileNavigationItem
import com.tunc.wayfolk.feature.profile.presentation.navigation.ProfileScreenEntryProvider
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.NavEntryProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val navigationModule = module {
    singleOf(::ProfileScreenEntryProvider) { bind<NavEntryProvider>() }
    singleOf(::ProfileNavigationItem) { bind<BottomNavigationItem>() }
}
