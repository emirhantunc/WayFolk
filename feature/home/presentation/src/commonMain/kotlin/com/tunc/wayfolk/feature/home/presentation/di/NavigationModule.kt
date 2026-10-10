package com.tunc.wayfolk.feature.home.presentation.di

import com.tunc.wayfolk.feature.home.presentation.navigation.HomeNavigationImpl
import com.tunc.wayfolk.feature.home.presentation.navigation.HomeNavigationItem
import com.tunc.wayfolk.feature.home.presentation.navigation.HomeRouter
import com.tunc.wayfolk.feature.home.presentation.navigation.HomeScreenEntryProvider
import com.tunc.wayfolk.feature.navigation.startdestination.HomeDestination
import com.tunc.wayfolk.navigation.home.HomeNavigation
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.NavEntryProvider
import com.tunc.wayfolk.navigation.model.router.ComposeRouter
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal val navigationModule = module {
    singleOf(::HomeNavigationImpl) { bind<HomeNavigation>() }
    singleOf(::HomeScreenEntryProvider) { bind<NavEntryProvider>() }
    singleOf(::HomeNavigationItem) { bind<BottomNavigationItem>() }
    single<ComposeRouter>(named<HomeDestination>()) { HomeRouter.Home }
}
