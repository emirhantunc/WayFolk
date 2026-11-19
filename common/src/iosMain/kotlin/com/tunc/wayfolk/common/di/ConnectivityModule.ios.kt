package com.tunc.wayfolk.common.di

import com.tunc.wayfolk.common.connectivity.ConnectivityObserver
import com.tunc.wayfolk.common.connectivity.IosConnectivityObserver
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val connectivityModule: Module = module {
    single<ConnectivityObserver> { IosConnectivityObserver() }
}
