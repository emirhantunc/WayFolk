package com.tunc.wayfolk.feature.search.di

import com.tunc.wayfolk.feature.search.presentation.di.searchPresentationModule
import org.koin.dsl.module

val searchModule
    get() = module {
        includes(
            searchPresentationModule,
        )
    }
