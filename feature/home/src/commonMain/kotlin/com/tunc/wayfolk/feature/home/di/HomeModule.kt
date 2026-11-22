package com.tunc.wayfolk.feature.home.di

import com.tunc.wayfolk.feature.home.data.di.homeDataModule
import com.tunc.wayfolk.feature.home.domain.usecase.GetHomeFeedUseCase
import com.tunc.wayfolk.feature.home.presentation.di.homePresentationModule
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val homeModule
    get() = module {
        includes(
            homeDataModule,
            homePresentationModule,
        )
        factoryOf(::GetHomeFeedUseCase)
    }
