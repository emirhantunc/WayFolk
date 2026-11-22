package com.tunc.wayfolk.feature.profile.presentation.di

import com.tunc.wayfolk.feature.profile.presentation.screen.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profilePresentationModule
    get() = module {
        includes(
            navigationModule
        )
        viewModelOf(::ProfileViewModel)
    }
