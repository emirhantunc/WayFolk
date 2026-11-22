package com.tunc.wayfolk.feature.rooms.di

import com.tunc.wayfolk.feature.rooms.presentation.di.roomsPresentationModule
import org.koin.dsl.module

val roomsModule
    get() = module {
        includes(
            roomsPresentationModule,
        )
    }
