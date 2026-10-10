package com.tunc.android.wayfolk.di

import com.tunc.android.wayfolk.navigation.NavigationTabProviderImpl
import com.tunc.android.wayfolk.navigation.StartDestinationProviderImpl
import com.tunc.wayfolk.feature.navigation.startdestination.HomeDestination
import com.tunc.wayfolk.feature.navigation.startdestination.SplashDestination
import com.tunc.wayfolk.navigation.StartDestinationProvider
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.NavigationTabProvider
import com.tunc.wayfolk.navigation.model.router.ComposeRouter
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val navigationModule = module {
    single<NavigationTabProvider> {
        NavigationTabProviderImpl(
            navigationItems = getAll<BottomNavigationItem>(),
            startDestination = get<ComposeRouter>(named<SplashDestination>())
        )
    }

    single<StartDestinationProvider> {
        StartDestinationProviderImpl(
            splashDestination = get<ComposeRouter>(named<SplashDestination>()),
            homeDestination = get<ComposeRouter>(named<HomeDestination>())
        )
    }
}
