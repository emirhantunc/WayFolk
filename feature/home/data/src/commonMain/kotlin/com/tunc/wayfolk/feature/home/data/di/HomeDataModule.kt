package com.tunc.wayfolk.feature.home.data.di

import com.tunc.wayfolk.feature.home.data.repository.HomeRepositoryImpl
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import org.koin.dsl.module

val homeDataModule
    get() = module {
        single<HomeRepository> {
            HomeRepositoryImpl(httpClient = get(), apiHandler = get())
        }
    }
