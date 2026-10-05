package com.tunc.wayfolk.core.presentation.di

import com.tunc.wayfolk.core.presentation.AppEventProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val corePresentationModule
    get() = module {
        singleOf(::AppEventProvider)
    }