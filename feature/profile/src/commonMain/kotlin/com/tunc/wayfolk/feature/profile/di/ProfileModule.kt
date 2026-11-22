package com.tunc.wayfolk.feature.profile.di

import com.tunc.wayfolk.feature.profile.presentation.di.profilePresentationModule
import org.koin.dsl.module

val profileModule
    get() = module {
        includes(
            profilePresentationModule,
        )
    }
