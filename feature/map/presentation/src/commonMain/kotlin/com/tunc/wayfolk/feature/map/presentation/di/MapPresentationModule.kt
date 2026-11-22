package com.tunc.wayfolk.feature.map.presentation.di

import com.tunc.wayfolk.feature.map.presentation.screen.MapViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mapPresentationModule
    get() = module {
        includes(
            navigationModule
        )
        viewModelOf(::MapViewModel)
    }
