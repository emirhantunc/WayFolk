package com.tunc.wayfolk.feature.home.presentation.di

import com.tunc.wayfolk.feature.home.presentation.screen.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule
    get() = module {
        includes(
            navigationModule
        )
        viewModelOf(::HomeViewModel)
    }
