package com.tunc.wayfolk.feature.home.data.di

import com.tunc.wayfolk.feature.home.data.fake.FakeHomeRepository
import com.tunc.wayfolk.feature.home.data.repository.HomeRepositoryImpl
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import kotlin.time.Clock
import org.koin.dsl.module

private const val USE_FAKE_HOME_FEED = true

val homeDataModule
    get() = module {
        single<HomeRepository> {
            if (USE_FAKE_HOME_FEED) {
                FakeHomeRepository(clock = Clock.System)
            } else {
                HomeRepositoryImpl(httpClient = get(), apiHandler = get())
            }
        }
    }
