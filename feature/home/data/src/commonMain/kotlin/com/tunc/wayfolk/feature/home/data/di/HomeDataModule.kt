package com.tunc.wayfolk.feature.home.data.di

import com.tunc.wayfolk.feature.home.data.remote.HomeApi
import com.tunc.wayfolk.feature.home.data.repository.HomeRepositoryImpl
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val homeDataModule
    get() = module {
        singleOf(::HomeApi)
        singleOf(::HomeRepositoryImpl) { bind<HomeRepository>() }
    }
