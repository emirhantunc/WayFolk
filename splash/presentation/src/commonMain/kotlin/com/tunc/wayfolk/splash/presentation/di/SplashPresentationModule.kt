package com.tunc.wayfolk.splash.presentation.di

import org.koin.dsl.module

val splashPresentationModule
    get() = module {
        includes(
            navigationModule,
        )
    }