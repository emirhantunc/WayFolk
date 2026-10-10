package com.tunc.wayfolk.feature.home.presentation.di

import org.koin.dsl.module

val homePresentationModule
    get() = module {
        includes(
            navigationModule
        )
    }
