package com.tunc.wayfolk.feature.search.presentation.di

import com.tunc.wayfolk.feature.search.presentation.navigation.SearchNavigationItem
import com.tunc.wayfolk.feature.search.presentation.navigation.SearchScreenEntryProvider
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.NavEntryProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val navigationModule = module {
    singleOf(::SearchScreenEntryProvider) { bind<NavEntryProvider>() }
    singleOf(::SearchNavigationItem) { bind<BottomNavigationItem>() }
}
