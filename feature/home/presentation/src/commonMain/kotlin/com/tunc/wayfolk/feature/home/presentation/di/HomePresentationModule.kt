package com.tunc.wayfolk.feature.home.presentation.di

import com.tunc.wayfolk.feature.home.presentation.mapper.HomeUiMapper
import com.tunc.wayfolk.feature.home.presentation.screen.HomeViewModel
import kotlin.time.Clock
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule
    get() = module {
        includes(
            navigationModule
        )
        single { HomeUiMapper(clock = Clock.System) }
        viewModelOf(::HomeViewModel)
    }
