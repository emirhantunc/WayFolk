package com.tunc.wayfolk.feature.search.presentation.di

import com.tunc.wayfolk.feature.search.presentation.screen.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchPresentationModule
    get() = module {
        includes(
            navigationModule
        )
        viewModelOf(::SearchViewModel)
    }
