package com.tunc.wayfolk.feature.map.di

import com.tunc.wayfolk.feature.map.presentation.di.mapPresentationModule
import org.koin.dsl.module

val mapModule
    get() = module {
        includes(
            mapPresentationModule,
        )
    }
