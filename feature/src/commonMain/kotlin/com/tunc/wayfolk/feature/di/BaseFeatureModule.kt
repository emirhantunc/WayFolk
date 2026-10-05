package com.tunc.wayfolk.feature.di

import com.tunc.wayfolk.feature.NavResultBusImpl
import com.tunc.wayfolk.feature.NavigationTabEventManagerImpl
import com.tunc.wayfolk.feature.navigation.AppComposeNavigatorImpl
import com.tunc.wayfolk.navigation.AppComposeNavigator
import com.tunc.wayfolk.navigation.NavResultBus
import com.tunc.wayfolk.navigation.NavigationTabEventManager
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val baseFeatureModules = module {
    singleOf(::AppComposeNavigatorImpl) { bind<AppComposeNavigator>() }
    singleOf(::NavResultBusImpl) { bind<NavResultBus>() }
    singleOf(::NavigationTabEventManagerImpl) { bind<NavigationTabEventManager>() }
}
