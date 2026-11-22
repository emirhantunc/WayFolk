package com.tunc.wayfolk.feature.rooms.presentation.di

import com.tunc.wayfolk.feature.rooms.presentation.screen.RoomsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val roomsPresentationModule
    get() = module {
        includes(
            navigationModule
        )
        viewModelOf(::RoomsViewModel)
    }
