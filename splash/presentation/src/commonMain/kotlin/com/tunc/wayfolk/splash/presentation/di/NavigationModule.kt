package com.tunc.wayfolk.splash.presentation.di

import com.tunc.wayfolk.feature.navigation.startdestination.SplashDestination
import com.tunc.wayfolk.navigation.model.NavEntryProvider
import com.tunc.wayfolk.navigation.model.router.ComposeRouter
import com.tunc.wayfolk.splash.presentation.navigation.SplashNavEntryProvider
import com.tunc.wayfolk.splash.presentation.navigation.SplashRouter
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val navigationModule = module {
    single<ComposeRouter>(named<SplashDestination>()) { SplashRouter.Splash }
    singleOf(::SplashNavEntryProvider){bind<NavEntryProvider>()}
}